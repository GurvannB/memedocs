package fr.epsi.exceptions;

import fr.epsi.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ErrorDto> handleControllerException(ControllerException exception) {
        return new ResponseEntity<>(
                ErrorDto.builder().status(exception.getStatus().value()).timestamp(LocalDateTime.now()).message(exception.getMessage()).build(),
                exception.getStatus()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException exception) {
        String errorMessage = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .map((err) -> ((FieldError) err).getField()+" "+err.getDefaultMessage()).orElse("");

        return new ResponseEntity<>(
                ErrorDto.builder().status(HttpStatus.BAD_REQUEST.value()).timestamp(LocalDateTime.now()).message(errorMessage).build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
