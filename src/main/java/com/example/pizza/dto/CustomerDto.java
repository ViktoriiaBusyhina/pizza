package com.example.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The CustomerDto class represents a data transfer object for a customer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String  address;
    private String phone;
    private String email;
    private String  paymentMethod;
    private String isBlocked;
}
