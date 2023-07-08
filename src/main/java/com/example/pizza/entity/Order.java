package com.example.pizza.entity;

import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
/**
 * The Order class represents an order entity.
 */
@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "cafe_id")
    private Integer cafeId;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

//проверка для нового заказа, для блокировки пользователя


    //енамы со статусами для оплаты, для статуса пицц
}
