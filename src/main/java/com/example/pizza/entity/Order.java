package com.example.pizza.entity;

import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@Table(name = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    //статус
    //способ оплаты
    // стринг кастомер айди

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

    @Column(name = "pizza_id")
    private Integer pizzaId;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "status_order")
    private StatusOrder statusOrder;


//проверка для нового заказа, для блокировки пользователя


    //енамы со статусами для оплаты, для статуса пицц
}
