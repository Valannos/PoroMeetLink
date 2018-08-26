package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.outils.enumerations.PaysEnum;
import fr.imie.poromeetlink.outils.enumerations.SexeEnum;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link ClassicDto} for {@link fr.imie.poromeetlink.domain.entities.Candidat}
 */
public class CandidatDto extends ClassicDto {

    private Long utilisateurId;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private SexeEnum sexe;

    private String ville;

    private String urlAvatar;

    private PaysEnum pays;

    private ZonedDateTime dateNaissance;

    private Set<DiplomeDto> diplomes = new HashSet<>();

    private Set<ExperienceDto> experiences = new HashSet<>();

    private Set<CompetenceCandidatDto> competenceCandidats = new HashSet<>();

    private Set<PropositionCandidatureDto> propositionCandidatures = new HashSet<>();

    private Set<SavoirEtreDto> savoirsEtres = new HashSet<>();

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public SexeEnum getSexe() {
        return sexe;
    }

    public void setSexe(SexeEnum sexe) {
        this.sexe = sexe;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public PaysEnum getPays() {
        return pays;
    }

    public void setPays(PaysEnum pays) {
        this.pays = pays;
    }

    public ZonedDateTime getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(ZonedDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Set<DiplomeDto> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(Set<DiplomeDto> diplomes) {
        this.diplomes = diplomes;
    }

    public Set<ExperienceDto> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<ExperienceDto> experiences) {
        this.experiences = experiences;
    }

    public Set<CompetenceCandidatDto> getCompetenceCandidats() {
        return competenceCandidats;
    }

    public void setCompetenceCandidats(Set<CompetenceCandidatDto> competenceCandidats) {
        this.competenceCandidats = competenceCandidats;
    }

    public Set<PropositionCandidatureDto> getPropositionCandidatures() {
        return propositionCandidatures;
    }

    public void setPropositionCandidatures(Set<PropositionCandidatureDto> propositionCandidatures) {
        this.propositionCandidatures = propositionCandidatures;
    }

    public Set<SavoirEtreDto> getSavoirsEtres() {
        return savoirsEtres;
    }

    public void setSavoirsEtres(Set<SavoirEtreDto> savoirsEtres) {
        this.savoirsEtres = savoirsEtres;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }
}
