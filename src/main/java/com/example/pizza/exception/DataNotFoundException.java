package com.example.pizza.exception;
/**
 * The DataNotFoundException class is an exception that indicates data was not found.
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * Constructs a new DataNotFoundException with no detail message.
     */
    public DataNotFoundException() {
    }

    /**
     * Constructs a new DataNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}