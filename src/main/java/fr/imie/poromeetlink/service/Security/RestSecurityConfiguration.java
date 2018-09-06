package fr.imie.poromeetlink.service.Security;

import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.service.filters.JWTAuthenticationFilter;
import fr.imie.poromeetlink.service.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    UtilisateurAuthService authService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtAuthenticationSuccessHandler successHandler;

    @Autowired
    JwtAuthenticationFailureHandler failureHandler;

    @Autowired
    RestEntryPoint RestEntryPoint;

    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;

    /**
     * Configuration CORS - Permets d'effecuter des requêtes HTTP depuis le serveur de développement Angular
     * @return la configuration CORS déclarée plus bas.
     */
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(1728000L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().configurationSource(corsConfigurationSource())

                .and()

                .csrf().disable()

             //   .addFilterBefore(getCorsFilter(), SessionManagementFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(RestEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)

                .and()

                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, UrlConstants.REGISTER_USER_URL).permitAll()
                .antMatchers(HttpMethod.POST, UrlConstants.LOGIN_URL).permitAll()
                .anyRequest().authenticated()

                .and()

                .addFilter(new JWTAuthenticationFilter(authenticationManager(), getApplicationContext(), successHandler, failureHandler))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), getApplicationContext()))

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .logout()
                .deleteCookies("token")
                .invalidateHttpSession(true)
             /*   .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL à appeler pour se délogguer
                .logoutSuccessUrl("/")                                        // URL vers laquelle renvoyer lorsqu'on s'est bien déloggué
                .deleteCookies("JSESSIONID")                                  // COOKIE à supprimer
                .invalidateHttpSession(true)*/
        ;



    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*.js", "/*.css", "/assets/**");


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);

    }


}
