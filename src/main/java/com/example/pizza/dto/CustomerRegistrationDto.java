package com.example.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Data Transfer Object (DTO) representing customer registration information.
 * This class is used to transfer customer registration data to the RegistrationController.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
}
