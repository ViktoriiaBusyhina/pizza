package com.example.pizza.entity;

import com.example.pizza.enam.PizzaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.security.Timestamp;
/**
 * The Pizza class represents a pizza entity.
 */
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

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "pizza_name")
    private String  pizzaName;

    @Column(name = "size")
    private Integer size;

    @Column(name = "status")
    private PizzaStatus status;

    @Column(name = "order_id")
    private Integer OrderId;

    @Column(name = "cafe_id")
    private Integer CafeId;

    @Column(name = "price")
    private BigInteger price;
}
