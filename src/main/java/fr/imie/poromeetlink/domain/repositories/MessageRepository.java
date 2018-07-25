package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link JpaRepository} pour l'entité {@link Message}
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
