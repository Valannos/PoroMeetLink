package fr.imie.poromeetlink.service.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.poromeetlink.domain.entities.Role;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.outils.constantes.SecurityConstants;
import fr.imie.poromeetlink.service.Security.tokens.JwtToken;
import fr.imie.poromeetlink.service.services.MessageProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationSuccessHandler.class);

    @Autowired
    MessageProvider messageProvider;

    @Autowired
    ObjectMapper mapper;

    private ZonedDateTime currentTime;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        Utilisateur user = (Utilisateur) authentication.getPrincipal();

        if (StringUtils.isNotBlank(user.getUsername())) {
            LOGGER.info(messageProvider.getMessage("SUCCESS_USER_AUTH") + user.getUsername());
            JwtToken token = this.createAccessToken(user);
            // response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
            // Cookie cookie = new Cookie("token", token);
            //  response.addCookie(cookie);
            ObjectMapper mapper = new ObjectMapper();
            if (token != null) {
                mapper.writeValue(response.getWriter(), token);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String createRefreshToken(Utilisateur user) {

        currentTime = ZonedDateTime.now();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        UUID uuid = UUID.randomUUID();
        claims.put("scopes", user.getRoles().stream().map(s -> s.getName()).collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plus(Duration.ofDays(SecurityConstants.EXPIRATION_TIME_REFRESH_TOKEN)).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .setId(uuid.toString())
                .compact();

    }

    private JwtToken createAccessToken(Utilisateur user) {

        currentTime = ZonedDateTime.now();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", user.getRoles().stream().map(s -> s.getName()).collect(Collectors.toList()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plus(Duration.ofMinutes(SecurityConstants.EXPIRATION_TIME)).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
        LOGGER.info("date_exp: " + claims.getExpiration());
        JwtToken jwtToken = new JwtToken(token);
        jwtToken.setExpirationDate(currentTime.plus(Duration.ofMinutes(SecurityConstants.EXPIRATION_TIME)).toInstant().toEpochMilli());

        return jwtToken;
    }


}
