package fr.imie.poromeetlink.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * {@link Entity} pour gérer les rôles
 */
@Entity
@Table(name = "role")
public class Role extends ClassicEntity implements GrantedAuthority {

    private static final long serialVersionUID = -3176539581773600561L;

    @Column
    private String name;

    /*
        @ManyToMany(mappedBy = "roles" )
        private Set<Utilisateur> utilisateurs = new HashSet<>();

        public Set<Utilisateur> getUtilisateurs() {
            return utilisateurs;
        }

        public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
            this.utilisateurs = utilisateurs;
        }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public Long getId() {
        return super.getId();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Role) {
            return name.equals(((Role) obj).name);
        }

        return false;
    }
}
