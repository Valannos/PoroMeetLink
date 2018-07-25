package fr.imie.poromeetlink.domain.entities;

import fr.imie.poromeetlink.outils.enumerations.TypeMessage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * {@link Entity} pour gérer les messages échangés entre utilisateurs
 */
@Entity
@Table(name = "message")
public class Message extends ClassicEntity implements Serializable {


    private static final long serialVersionUID = 2340160784892448375L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_expediteur")
    private Utilisateur expediteur;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_destinataire")
    private Utilisateur destinataire;

    @NotNull
    @Column(nullable = false)
    private String contenu;

    @Enumerated(EnumType.STRING)
    private TypeMessage typeMessage;

    @ManyToOne
    @JoinColumn(nullable = true, name = "id_annonce")
    private Candidature candidature;

    @Column(name = "date_envoi", nullable = false)
    private ZonedDateTime dateEnvoi;

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }
}
