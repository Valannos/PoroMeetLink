package fr.imie.poromeetlink.service.filters;

import fr.imie.poromeetlink.domain.entities.Role;
import fr.imie.poromeetlink.outils.constantes.SecurityConstants;
import fr.imie.poromeetlink.service.services.MessageProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private final MessageProvider messageProvider;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, ApplicationContext context) {
        super(authenticationManager);
        messageProvider = context.getBean(MessageProvider.class);

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = null;
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            chain.doFilter(request, response);
            return;

        }
        try {
            Jws<Claims> jwsClaims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            String user = jwsClaims
                    .getBody()
                    .getSubject();
            LOGGER.info("token de l'utilisateur:  " + user);
            List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
            List<Role> authorities = scopes.stream()
                    .map(authority -> new Role(authority))
                    .collect(Collectors.toList());
            LOGGER.info("Rôles: ");
            authorities.forEach(grantedAuthority -> LOGGER.info(grantedAuthority.getName()));

            if (user != null) {

                authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (JwtException e) {
            LOGGER.error(e.getMessage());

        } catch (AuthenticationException e) {
            LOGGER.error(e.getMessage());
            onUnsuccessfulAuthentication(request, response, e);
        }

        chain.doFilter(request, response);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        SecurityContextHolder.clearContext();

    }
}
