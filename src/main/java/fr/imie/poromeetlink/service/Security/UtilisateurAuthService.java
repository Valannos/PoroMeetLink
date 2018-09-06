package fr.imie.poromeetlink.service.Security;

import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UtilisateurAuthService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = service.findByLogin(username);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        utilisateur.getRoles().forEach(role ->

                grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getAuthority())
                ));

        return utilisateur;
    }
}
