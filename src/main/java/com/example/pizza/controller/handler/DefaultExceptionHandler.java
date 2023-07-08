package com.example.pizza.controller.handler;
import com.example.pizza.exception.OrderNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The DefaultExceptionHandler class is a controller advice class that handles exceptions.
 */
@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * Handles OrderNotAllowedException and IllegalArgumentException.
     *
     * @param exception the exception to handle
     * @return a ResponseEntity with HTTP status indicating the result of the exception handling
     */
    @ExceptionHandler({OrderNotAllowedException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception exception) {
        log.info("Error: " + exception);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
