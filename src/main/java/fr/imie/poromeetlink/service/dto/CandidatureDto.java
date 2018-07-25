package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class CandidatureDto {

    @NotNull
    private PropositionCandidatureDto propositionCandidature;

    private Set<MessageDto> message = new HashSet<>();

    private Boolean estCloturee = false;

    @NotNull
    private ZonedDateTime dateCreation;

    public PropositionCandidatureDto getPropositionCandidature() {
        return propositionCandidature;
    }

    public void setPropositionCandidature(PropositionCandidatureDto propositionCandidature) {
        this.propositionCandidature = propositionCandidature;
    }

    public Set<MessageDto> getMessage() {
        return message;
    }

    public void setMessage(Set<MessageDto> message) {
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
