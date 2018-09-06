package fr.imie.poromeetlink.service.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.service.Security.JwtAuthenticationFailureHandler;
import fr.imie.poromeetlink.service.Security.JwtAuthenticationSuccessHandler;
import fr.imie.poromeetlink.service.services.MessageProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final JwtAuthenticationSuccessHandler successHandler;

    private final JwtAuthenticationFailureHandler failureHandler;

    private AuthenticationManager authenticationManager;

    private MessageProvider messageProvider;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext applicationContext, JwtAuthenticationSuccessHandler successHandler, JwtAuthenticationFailureHandler failureHandler) {

        this.authenticationManager = authenticationManager;
        this.messageProvider = applicationContext.getBean(MessageProvider.class);
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
        setFilterProcessesUrl(UrlConstants.LOGIN_URL);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            Utilisateur creds = new ObjectMapper()
                    .readValue(request.getInputStream(), Utilisateur.class);

            if (!StringUtils.isNotBlank(creds.getPassword()) || !StringUtils.isNotBlank(creds.getPassword())) {
                throw new InsufficientAuthenticationException(messageProvider.getMessage("NOT_CRED"));
            }

            if (!creds.isEnabled()) {
                throw new DisabledException(messageProvider.getMessage("ACCOUNT_DISABLE"));
            }
            if (!creds.isAccountNonExpired()) {
                throw new AccountExpiredException(messageProvider.getMessage("ACCOUNT_EXPIRED"));
            }

            if (!creds.isAccountNonLocked()) {
                throw new LockedException(messageProvider.getMessage("ACCOUNT_LOCKED"));
            }
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), creds.getPassword(), new ArrayList<>()
            ));

        } catch (IOException ex) {

            throw new BadCredentialsException(messageProvider.getMessage("ERROR_CRED"));
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
