package fr.imie.poromeetlink.outils.errors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import org.springframework.http.HttpStatus;

public class JwtErrorEntity extends AbstractError {
    protected JwtErrorEntity(HttpStatus status, String message, Header header, Claims claims) {
        super(status, message);
        this.claims = claims;
        this.header = header;
    }

    private Claims claims;

    private Header header;

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
