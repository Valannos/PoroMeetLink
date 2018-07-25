package fr.imie.poromeetlink.outils.errors;

import org.springframework.http.HttpStatus;

/**
 * Classe fille simple de {@link AbstractError}
 */
public class BasicErrorEntity extends AbstractError {
    public BasicErrorEntity(HttpStatus status, String message, String url) {
        super(status, message);
        this.url = url;
    }

    public BasicErrorEntity(HttpStatus status, String message) {
        super(status, message);
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
