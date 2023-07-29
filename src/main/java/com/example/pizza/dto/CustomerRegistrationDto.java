package com.example.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
