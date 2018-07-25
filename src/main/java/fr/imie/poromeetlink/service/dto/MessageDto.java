package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.outils.enumerations.TypeMessage;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class MessageDto extends ClassicDto {

    @NotNull

    private UtilisateurDto expediteur;

    @NotNull
    private UtilisateurDto destinataire;

    @NotNull
    private String contenu;

    private TypeMessage typeMessage;

    private CandidatureDto candidature;

    private ZonedDateTime dateEnvoi;

    public UtilisateurDto getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(UtilisateurDto expediteur) {
        this.expediteur = expediteur;
    }

    public UtilisateurDto getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(UtilisateurDto destinataire) {
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

    public CandidatureDto getCandidature() {
        return candidature;
    }

    public void setCandidature(CandidatureDto candidature) {
        this.candidature = candidature;
    }

    public ZonedDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(ZonedDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
}
