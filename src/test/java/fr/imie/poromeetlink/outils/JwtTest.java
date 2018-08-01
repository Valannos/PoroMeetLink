package fr.imie.poromeetlink.outils;

import fr.imie.poromeetlink.outils.constantes.SecurityConstants;
import fr.imie.poromeetlink.service.Security.tokens.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Profile("test")
public class JwtTest {

    public String createJWT(String username, String... roles) {
      ZonedDateTime  currentTime = ZonedDateTime.now();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("scopes", roles);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plus(Duration.ofMinutes(SecurityConstants.EXPIRATION_TIME)).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();

        JwtToken jwtToken = new JwtToken(token);
        jwtToken.setExpirationDate(currentTime.plus(Duration.ofMinutes(SecurityConstants.EXPIRATION_TIME)).toInstant().toEpochMilli());
        return jwtToken.getToken();
    }



}
