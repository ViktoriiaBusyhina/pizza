package com.example.pizza.exception;

/**
 * Exception thrown when an order is not allowed.
 */
public class OrderNotAllowedException extends RuntimeException {

    /**
     * Constructs a new OrderNotAllowedException with the specified error message.
     *
     * @param message the error message
     */
    public OrderNotAllowedException(String message) {
        super(message);
    }
}
