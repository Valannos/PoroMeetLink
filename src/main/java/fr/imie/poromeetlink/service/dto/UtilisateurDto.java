package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.domain.entities.Role;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe DTO pour {@link fr.imie.poromeetlink.domain.entities.Utilisateur}
 */
public class UtilisateurDto extends ClassicDto {

    @NotNull
    private String username;
    private Set<Role> roles = new HashSet<>();
    @NotNull
    // @Pattern(regexp = EntityUtils.PASSWORD)

    private String password;

    private Boolean superAdmin;

    private Set<MessageDto> messagesEnvoyes = new HashSet<>();
    private Set<MessageDto> messagesRecus = new HashSet<>();

    private Long employeId;
    private Long candidatId;

    @NotNull
    private String email;

    private ZonedDateTime dateBloquage;

    private ZonedDateTime dateDesactivation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MessageDto> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    public void setMessagesEnvoyes(Set<MessageDto> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }

    public Set<MessageDto> getMessagesRecus() {
        return messagesRecus;
    }

    public void setMessagesRecus(Set<MessageDto> messagesRecus) {
        this.messagesRecus = messagesRecus;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public ZonedDateTime getDateBloquage() {
        return dateBloquage;
    }

    public void setDateBloquage(ZonedDateTime dateBloquage) {
        this.dateBloquage = dateBloquage;
    }

    public ZonedDateTime getDateDesactivation() {
        return dateDesactivation;
    }

    public void setDateDesactivation(ZonedDateTime dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}
