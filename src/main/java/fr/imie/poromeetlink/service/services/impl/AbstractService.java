package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.BaseEntity;
import fr.imie.poromeetlink.service.services.MessageProvider;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite que doivent étendre les implémentations de service.
 *
 * @param <R> Un repository qui sera injecté.
 */

public abstract class AbstractService<R extends JpaRepository<? extends BaseEntity, ?>> {

    protected List<String> invalidFields = new ArrayList<>();

    protected List<String> nonUniqueFields = new ArrayList<>();

    protected List<String> invalidDateFields = new ArrayList<>();

    @Autowired
    protected MessageProvider messageProvider;

    @Autowired
    UtilisateurService utilisateurService;

    /**
     * {@link JpaRepository} pour une entité
     */
    @Autowired
    protected R repository;


}
