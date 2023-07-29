package com.example.pizza.controller.handler;

import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.exception.OrderNotAllowedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * The DefaultExceptionHandlerTest class is a unit test class for DefaultExceptionHandler.
 * <p>
 * It tests the exception handling methods in DefaultExceptionHandler.
 */
@ExtendWith(MockitoExtension.class)
class DefaultExceptionHandlerTest {

    @Mock
    Exception exception;

    @InjectMocks
    DefaultExceptionHandler exceptionHandler;

    /**
     * Tests the handleException method for handling OrderNotAllowedException.
     * <p>
     * It asserts that a ResponseEntity with status code HttpStatus.BAD_REQUEST is returned.
     */
    @Test
    void handleException_OrderNotAllowedException_ShouldReturnBadRequest_ok() {
// Arrange
        OrderNotAllowedException orderNotAllowedException = new OrderNotAllowedException("Order not allowed");

// Act
        ResponseEntity<String> response = exceptionHandler.handleException(orderNotAllowedException);

// Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(exception, never()).getMessage();
    }

    /**
     * Tests the handleException method for handling IllegalArgumentException.
     * <p>
     * It asserts that a ResponseEntity with status code HttpStatus.BAD_REQUEST is returned.
     */
    @Test
    void handleException_IllegalArgumentException_ShouldReturnBadRequest_ok() {
// Arrange
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();

// Act
        ResponseEntity<String> response = exceptionHandler.handleException(illegalArgumentException);

// Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(exception, never()).getMessage();
    }

    /**
     * Tests the handleDataNotFoundException method.
     * <p>
     * It asserts that a ResponseEntity with status code HttpStatus.NOT_FOUND is returned.
     */
    @Test
    void handleDataNotFoundException_ShouldReturnNotFound_ok() {
// Arrange
        DataNotFoundException dataNotFoundException = new DataNotFoundException();

// Act
        ResponseEntity<String> response = exceptionHandler.handleDataNotFoundException(dataNotFoundException);

// Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(exception, never()).getMessage();
    }
}