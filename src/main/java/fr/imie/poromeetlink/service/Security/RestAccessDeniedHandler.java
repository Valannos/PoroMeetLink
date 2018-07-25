package fr.imie.poromeetlink.service.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.poromeetlink.outils.errors.BasicErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public RestAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        BasicErrorEntity errorEntity = new BasicErrorEntity(HttpStatus.valueOf(response.getStatus()), accessDeniedException.getMessage(), request.getRequestURI());
        objectMapper.writeValue(response.getWriter(), errorEntity);
    }
}
