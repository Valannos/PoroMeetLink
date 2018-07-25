package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Role;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Utilisateur.class})
public abstract class RoleMapper {


    String roleToString(Role role) {
        return role.getAuthority();
    }

    @Mappings({
            @Mapping(source = "s", target = "name")
    })
    abstract Role stringToRole(String s);

    abstract List<String> roleToString(List<Role> roles);

    abstract List<Role> stringToRole(List<String> strings);

}
