package fr.epsi.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ControllerException extends RuntimeException {
    private HttpStatus status;

    public ControllerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
