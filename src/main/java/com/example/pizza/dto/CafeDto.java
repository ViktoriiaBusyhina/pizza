package com.example.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * The CafeDto class represents a data transfer object for a cafe.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CafeDto {
    private Integer pizzaId;
    private String name;
    private String address;
    private String phone;
    private String schedule;
}
