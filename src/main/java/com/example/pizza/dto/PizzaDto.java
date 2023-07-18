package com.example.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The PizzaDto class represents a data transfer object for a pizza.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDto {
    private Integer id;
    private String pizzaName;
    private String size;
    private Integer quantity;
    private String status;
    private Integer orderId;
    private Integer cafeId;
    private BigDecimal price;
}
