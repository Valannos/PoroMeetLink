package fr.imie.poromeetlink.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Entity} pour gérer les candidatures
 */
@Entity
@Table(name = "candidature")
public class Candidature extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = -1247473813231918398L;
    @NotNull
    @OneToOne
    private PropositionCandidature propositionCandidature;

    @OneToMany
    private Set<Message> message = new HashSet<>();

    @Column(nullable = false, name = "est_cloturee")
    private Boolean estCloturee = false;

    @NotNull
    @Column(nullable = false, name = "date_creation")
    private ZonedDateTime dateCreation;

    public PropositionCandidature getPropositionCandidature() {
        return propositionCandidature;
    }

    public void setPropositionCandidature(PropositionCandidature propositionCandidature) {
        this.propositionCandidature = propositionCandidature;
    }

    public Set<Message> getMessage() {
        return message;
    }

    public void setMessage(Set<Message> message) {
        this.message = message;
    }

    public Boolean getEstCloturee() {
        return estCloturee;
    }

    public void setEstCloturee(Boolean estCloturee) {
        this.estCloturee = estCloturee;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
