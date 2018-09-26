package fr.imie.poromeetlink.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Classe à intégrer dans les services pour que ces derniers génèrent un message
 * Attention : depuis les filtres, l'annotaion @{@link Autowired} ne fonctionne pas. Il faut utiliser {@link org.springframework.context.ApplicationContext}
 *
 */
@Configuration
public class MessageProvider {

    @Autowired
    private MessageSource messageSource;

    /**
     * Retourne un message personnalisé en lui associant un objet métier.
     * Ex : "{0} - Objet non trouvé" affichera, si tClass est égal à Secteur, le message Secteur - Objet non trouvé.
     *
     * @param code   La chaîne associée au message déclaré dans les fichiers .properties
     * @param tClass La classe associée à l'objet métier
     * @return Un message personnalisé incluant le nom d'une classe
     */
    public String getMessage(String code, Class<?> tClass) {

        return messageSource.getMessage(code, new Object[]{tClass.getSimpleName()}, LocaleContextHolder.getLocale());
    }

    /**
     * Retourne un message sans passage de paramètre.
     *
     * @param code
     * @return
     */
    public String getMessage(String code) {

        return messageSource.getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());

    }

    /**
     * Retourne un message avec passage de paramètre d'un type indéfini.
     * @param code Le code du message
     * @param objects Tableau d'object à passer en paramètre au message
     * @return Le message sous forme d'une chaîne de charactères.
     */
    public String getMessage(String code, Object[] objects) {
        return messageSource.getMessage(code, objects, LocaleContextHolder.getLocale());
    }
    
    @Bean
    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(this.messageSource);
        return bean;
    }
}
