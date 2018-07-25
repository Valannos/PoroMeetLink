package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * {@link JpaRepository} pour les {@link Role}
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Cherche et récupère un {@link Role} par son intitulé.
     * @param name l'intitulé sous forme de chaîne de charactères.
     * @return Un {@link Optional} pivant contenir ou non le {@link Role}
     */
    Optional<Role> findByName(String name);

}
