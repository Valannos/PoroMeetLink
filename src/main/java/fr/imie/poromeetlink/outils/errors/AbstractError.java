package fr.imie.poromeetlink.outils.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * Classe abstraite que chaque entité chargé de renvoyer une erreur suite à une levée d'exception doit hériter.
 */
public abstract class AbstractError {

    protected AbstractError(HttpStatus status, String message) {
        this.status = status;
        this.timestamp = ZonedDateTime.now();
        this.message = message;
    }

    protected HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected ZonedDateTime timestamp;

    protected String message;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
