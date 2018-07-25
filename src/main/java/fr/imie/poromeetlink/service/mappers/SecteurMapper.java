package fr.imie.poromeetlink.service.mappers;


import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.domain.entities.Secteur;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * {@link ma.glasnost.orika.Mapper} pour les {@link Secteur}
 */
@Component
public class SecteurMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Secteur.class, SecteurDto.class).byDefault().register();
    }
}
