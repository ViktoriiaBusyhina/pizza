package com.example.pizza.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "pizza")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Integer pizzaId;

    @Column(name = "pizza_name")
    private String  pizzaName;

    @Column(name = "size")
    private Integer size;

}
