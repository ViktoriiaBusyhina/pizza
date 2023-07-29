package com.example.pizza.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderNotAllowedExceptionTest {
    @Test
    public void testConstructor_withNoDetailMessage_shouldCreateExceptionWithoutMessage_ok() {
        // Arrange & Act
        DataNotFoundException exception = new DataNotFoundException();

        // Assert
        assertNull(exception.getMessage());
    }

    @Test
    public void testConstructor_withDetailMessage_shouldCreateExceptionWithMessage_ok() {
        // Arrange
        String message = "Data not found";

        // Act
        DataNotFoundException exception = new DataNotFoundException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}