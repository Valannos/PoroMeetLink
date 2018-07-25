package fr.imie.poromeetlink;

import fr.imie.poromeetlink.domain.entities.Role;
import fr.imie.poromeetlink.domain.repositories.RoleRepository;
import fr.imie.poromeetlink.domain.repositories.UtilisateurRepository;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

/**
 * @deprecated roles générés depuis un script liquibase
 */
public class DataInitiator implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitiator.class);

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Role> roles = Arrays.asList(

                new Role(RoleUtils.ADMINISTRATEUR_SITE),
                new Role(RoleUtils.ADMINISTRATEUR_COMPTE),
                new Role(RoleUtils.CANDIDAT),
                new Role(RoleUtils.DEMARCHEUR),
                new Role(RoleUtils.GERANT),
                new Role(RoleUtils.RECRUTEUR),
                new Role(RoleUtils.SUPER_CONTROLEUR),
                new Role(RoleUtils.SUPER_RAPPORTEUR)
        );

        roles.forEach(role -> {
            if (!roleRepository.findByName(role.getAuthority()).isPresent()) {
                roleRepository.save(role);
                LOGGER.info("Role added: " + role.getAuthority());
            } else {
                LOGGER.info("Role already present");
            }

        });
    }
}
