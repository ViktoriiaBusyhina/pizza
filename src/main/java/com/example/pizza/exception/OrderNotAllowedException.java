package com.example.pizza.exception;

public class OrderNotAllowedException extends RuntimeException {

    public OrderNotAllowedException(String message) {
        super(message);
    }
}
