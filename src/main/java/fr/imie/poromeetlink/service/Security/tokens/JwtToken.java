package fr.imie.poromeetlink.service.Security.tokens;

import java.io.Serializable;

/**
 * Classe de gestion des tokens JWT
 */
public class JwtToken implements Serializable {

    /**
	 * serial
	 */
	private static final long serialVersionUID = -4671164915948960526L;

	private String token;

    private Long expirationDate;

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtToken(String token) {
        this.token = token;

          }
}
