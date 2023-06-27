package com.example.pizza.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer customerId;

    @Column(name = "name")
    private Integer name;

    @Column(name = "address")
    private Integer address;//

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "email")
    private Integer email;


}
