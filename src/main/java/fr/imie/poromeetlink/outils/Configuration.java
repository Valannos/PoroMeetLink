package fr.imie.poromeetlink.outils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        LOGGER.info("Encoder loaded.");
        return new BCryptPasswordEncoder();
    }
    
    
}
