package com.example.pizza.entity;

import com.example.pizza.enam.PizzaName;
import com.example.pizza.enam.PizzaSize;
import com.example.pizza.enam.PizzaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
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
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "pizza_name")
    @Enumerated(EnumType.STRING)
    private PizzaName pizzaName;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PizzaStatus status;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "cafe_id")
    private Integer cafeId;

    @Column(name = "price")
    private BigDecimal price;
}
