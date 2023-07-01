package com.example.pizza.controller.handler;
import com.example.pizza.exception.OrderNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    @ExceptionHandler ({OrderNotAllowedException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException (Exception exception) {
        log.info("Error " + exception);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
