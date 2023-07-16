package com.example.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * The OrderDto class represents a data transfer object for an order.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer customerId;
    private Integer cafeId;
    private String paymentMethod;
    private String orderStatus;
    private String paymentStatus;
}
