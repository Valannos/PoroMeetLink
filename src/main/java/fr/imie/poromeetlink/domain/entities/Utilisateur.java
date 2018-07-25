package fr.imie.poromeetlink.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link Entity} pour gérer les utilisateurs
 */
@Entity
@Table(name = "utilisateur")
public class Utilisateur extends ClassicEntity implements UserDetails {

    /**
     * Serial
     */
    private static final long serialVersionUID = 2251292063389422153L;

    @Column(name = "is_super_admin", nullable = false, columnDefinition = "boolean default false")
    @NotNull
    private Boolean superAdmin;

    @NotNull
    @Column(length = 50, nullable = false)
    private String username;

    @NotNull
    @Column
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_utilisateurs",
            joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    //@Pattern(regexp = EntityUtils.PASSWORD)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "expediteur", fetch = FetchType.LAZY)
    private Set<Message> messagesEnvoyes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "destinataire", fetch = FetchType.LAZY)
    private Set<Message> messagesRecus = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
    private Employe employe;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
    private Candidat candidat;

    @Column(name = "date_bloquage")
    private ZonedDateTime dateBloquage;

    @Column(name = "date_desactivation")
    private ZonedDateTime dateDesactivation;

    @PrePersist
    public void prePersist() {
        if (this.getSuperAdmin() == null)
            this.setSuperAdmin(false);
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Message> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    public void setMessagesEnvoyes(Set<Message> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }

    public Set<Message> getMessagesRecus() {
        return messagesRecus;
    }

    public void setMessagesRecus(Set<Message> messagesRecus) {
        this.messagesRecus = messagesRecus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return dateBloquage == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return dateDesactivation == null;
    }

    public void setDateBloquage(ZonedDateTime dateBloquage) {
        this.dateBloquage = dateBloquage;
    }

    public void setDateDesactivation(ZonedDateTime dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    /**
     * Vérifie si l'utilisateur possède le rôle dont le nom est passé en paramètre.
     * Utiliser de préférences les constantes enregistrées dans {@link RoleUtils}
     * @param role nom du rôle
     * @return true si le rôle est trouvé par ceux que l'utilisateur possède.
     */
    public Boolean hasRole(String role) {

      return !this.getAuthorities()
                .stream()
                .filter(grantedAuthorities -> grantedAuthorities.getAuthority().equals(role))
                .collect(Collectors.toList()).isEmpty();

    }

    public ZonedDateTime getDateBloquage() {
        return dateBloquage;
    }

    public ZonedDateTime getDateDesactivation() {
        return dateDesactivation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
