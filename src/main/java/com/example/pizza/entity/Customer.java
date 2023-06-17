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
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name")
    private Integer customerName;

    @Column(name = "customer_address")
    private Integer customerAddress;

    @Column(name = "customer_phone")
    private Integer customerPhone;

    @Column(name = "customer_email")
    private Integer customerEmail;


}
