package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Role;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.domain.repositories.RoleRepository;
import fr.imie.poromeetlink.domain.repositories.UtilisateurRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.constantes.SecurityConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.Security.tokens.JwtToken;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import fr.imie.poromeetlink.service.mappers.UtilisateurMapper;
import fr.imie.poromeetlink.service.services.MessageProvider;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl extends AbstractService<UtilisateurRepository> implements UtilisateurService {

    @Autowired
    UtilisateurMapper utilisateurMapper;

    @Autowired
    MessageProvider messageProvider;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UtilisateurDto> getAll() {

        List<Utilisateur> utilisateurs = repository.findAll();
        utilisateurs.forEach(utilisateur -> {

            Hibernate.initialize(utilisateur.getEmploye());
            Hibernate.initialize(utilisateur.getCandidat());

        });
        return utilisateurMapper.utilisateurToDto(utilisateurs);
    }

    @Override
    public UtilisateurDto getOne(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Utilisateur.class));
        }

        Optional<Utilisateur> utilisateur = repository.findById(id);

        if (utilisateur.isPresent()) {
            return utilisateurMapper.utilisateurToDto(utilisateur.get());
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Utilisateur.class));
        }
    }

    @Override
    public UtilisateurDto saveOne(UtilisateurDto dto) throws InvalidRoleException, InvalidFieldException, InsuffisantRightsException, NoSuchFieldException {

        Utilisateur utilisateur;

        Set<Role> roles = new HashSet<>();
        if (dto != null) {
            if (!this.loginValidator(dto.getUsername())) {

                invalidFields.add(FieldUtils.USERNAME);

                throw new InvalidFieldException(messageProvider.getMessage("LOGIN_ALREADY_EXISTS", Utilisateur.class), invalidFields);

            }

            validator(dto);

            if (invalidFields.isEmpty()) {
                utilisateur = utilisateurMapper.dtoToUtilisateur(dto);
                utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
                for (Role role : dto.getRoles()) {

                    if (checkRole(role))
                        roles.add(roleRepository.findByName(role.getAuthority()).get());
                }
                utilisateur.setRoles(roles);
                utilisateur = repository.save(utilisateur);
            } else {
                throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Utilisateur.class), invalidFields);
            }
        } else {
            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Utilisateur.class));
        }
        return utilisateurMapper.utilisateurToDto(utilisateur);
    }

    @Override
    public Boolean checkRole(Role role) throws InvalidRoleException, InsuffisantRightsException {

        Optional<Role> registeredRole = roleRepository.findByName(role.getAuthority());
        if (!registeredRole.isPresent())
            throw new InvalidRoleException(messageProvider.getMessage("INVALID_ROLE", new Object[]{role.getAuthority()}));

        if (role.getAuthority().equals(RoleUtils.ADMINISTRATEUR_SITE)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.getAuthorities()
                    .stream()
                    .filter(grantedAuthorities -> grantedAuthorities.getAuthority().equals(RoleUtils.ADMINISTRATEUR_SITE))
                    .collect(Collectors.toList()).isEmpty()) {
                LOGGER.error(messageProvider.getMessage("DROITS_INSUFFISANTS", new Object[]{RoleUtils.ADMINISTRATEUR_SITE}));
                throw new InsuffisantRightsException(messageProvider.getMessage("DROITS_INSUFFISANTS", new Object[]{RoleUtils.ADMINISTRATEUR_SITE}), RoleUtils.ADMINISTRATEUR_SITE);
            }

        }
        return true;
    }

    @Override
    public JwtToken refreshToken(String jwtToken) throws WrongOwnerException {

        ZonedDateTime now = ZonedDateTime.now();
        Jws<Claims> claimsJwt = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET.getBytes())
                .parseClaimsJws(jwtToken);
        Utilisateur utilisateur = this.findByLogin(claimsJwt.getBody().getSubject());
        JwtToken newToken;

        if (utilisateur != null) {

            Utilisateur logged = this.getAuthenticatedUtilisateur();

            if (logged.getUsername().equals(utilisateur.getUsername())) {
                List<String> scopes = claimsJwt.getBody().get("scopes", List.class);
                List<Role> roles = scopes.stream().map(Role::new).collect(Collectors.toList());
                Claims claims = Jwts.claims().setSubject(utilisateur.getUsername());
                claims.put("scopes", utilisateur.getRoles().stream().map(s -> s.getName()).collect(Collectors.toList()));
                String token = Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(Date.from(now.toInstant()))
                        .setExpiration(Date.from(now.plus(Duration.ofMinutes(SecurityConstants.EXPIRATION_TIME)).toInstant()))
                        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                        .compact();
                newToken = new JwtToken(token);
                newToken.setExpirationDate(now.plus(Duration.ofMinutes(SecurityConstants.EXPIRATION_TIME)).toInstant().toEpochMilli());
                LOGGER.info("Génération d'un nouveau token pour l'utilisateur: " + logged.getUsername());
                return newToken;
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            }

        } else {
            throw new JwtException(messageProvider.getMessage("USERNAME_NOT_FOUND"));
        }

    }

    @Override
    public Boolean validateLoggedUser(String username) {

        Utilisateur utilisateur = this.getAuthenticatedUtilisateur();

        Optional<Utilisateur> user = repository.findByUsername(username);

        return user.filter(utilisateur::equals).isPresent();
    }

    @Override
    public UtilisateurDto updateOne(UtilisateurDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws InvalidRoleException {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else {
            throw new InvalidRoleException(messageProvider.getMessage("INVALID_FIELD", Utilisateur.class));
        }
        return true;
    }

    @Override
    public UtilisateurDto suspendre(Long id) throws EntryNotFound {

        Optional<Utilisateur> utilisateur = repository.findById(id);
        if (utilisateur.isPresent()) {

            if ((utilisateur.get().isAccountNonLocked())) {
                LOGGER.info(utilisateur.get().getUsername() + ": COMPTE SUSPENDU");
                utilisateur.get().setDateBloquage(ZonedDateTime.now());
            } else {
                LOGGER.info(utilisateur.get().getUsername() + ": COMPTE DEBLOQUE");
                utilisateur.get().setDateBloquage(null);
            }
            return utilisateurMapper.utilisateurToDto(repository.save(utilisateur.get()));

        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Utilisateur.class));
        }
    }

    @Override
    public Boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void validator(UtilisateurDto dto) {
        invalidFields.clear();
        invalidDateFields.clear();
        Class<? extends UtilisateurDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.LIST_ROLES:
                    if (dto.getRoles() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.USERNAME:
                    if (dto.getUsername() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.PASSWORD:
                    if (dto.getPassword() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.EMAIL:
                    if (dto.getEmail() == null || dto.getEmail().equals(StringUtils.EMPTY)) {
                        this.invalidFields.add(fieldName);
                    }
                    break;

            }
        });

    }

    @Override
    public Utilisateur getAuthenticatedUtilisateur() {
        return this.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public UtilisateurDto getAuthenticatedUtilisateurDto() {
        Utilisateur utilisateur = this.getAuthenticatedUtilisateur();
        return utilisateurMapper.utilisateurToDto(utilisateur);
    }

    @Override
    public Boolean loginValidator(String login) {

        Optional<Utilisateur> utilisateur = repository.findByUsername(login);
        return !utilisateur.isPresent();
    }

    @Override
    public Utilisateur findByLogin(String username) {
        Optional<Utilisateur> utilisateur = repository.findByUsername(username);
        if (utilisateur.isPresent()) {
            Hibernate.initialize(utilisateur.get().getRoles());
            return utilisateur.get();
        } else {
            throw new UsernameNotFoundException("USERNAME_NOT_FOUND");
        }

    }
}
