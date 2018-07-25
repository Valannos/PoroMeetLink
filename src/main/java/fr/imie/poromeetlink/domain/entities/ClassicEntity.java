package fr.imie.poromeetlink.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe de base pour les entités qui leur fournit à toutes un identifiant en base.
 * Attention : ne convient pas aux entités qui utilisent une clé composite.
 *
 */
@MappedSuperclass
public abstract class ClassicEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
