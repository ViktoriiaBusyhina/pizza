package com.example.pizza.entity;

import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "status_order")
    private StatusOrder statusOrder;





    //енамы со статусами для оплаты, для статуса пицц
}
