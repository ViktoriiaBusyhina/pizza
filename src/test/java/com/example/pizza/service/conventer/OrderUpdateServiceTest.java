package com.example.pizza.service.conventer;

import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderUpdateServiceTest {

    @Test
    void testConvert_withUpdates_shouldUpdateOrderObject_ok() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setPaymentMethod(PaymentMethod.CARD);
        order.setOrderStatus(OrderStatus.PROCESSING);

        Order orderUpdate = new Order();
        orderUpdate.setCustomerId(2);
        orderUpdate.setPaymentMethod(PaymentMethod.CASH_TO_THE_COURIER);

        OrderUpdateService orderUpdateService = new OrderUpdateService();

        // Act
        Order updatedOrder = orderUpdateService.convert(order, orderUpdate);

        // Assert
        assertEquals(2, updatedOrder.getCustomerId());
        assertEquals(PaymentMethod.CASH_TO_THE_COURIER, updatedOrder.getPaymentMethod());
        assertEquals(OrderStatus.PROCESSING, updatedOrder.getOrderStatus());
    }

    @Test
    void testConvert_withNullUpdates_shouldNotUpdateOrderObject_ok() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setPaymentMethod(PaymentMethod.CARD);
        order.setOrderStatus(OrderStatus.PROCESSING);

        Order orderUpdate = new Order(); // All attributes are null

        OrderUpdateService orderUpdateService = new OrderUpdateService();

        // Act
        Order updatedOrder = orderUpdateService.convert(order, orderUpdate);

        // Assert
        assertEquals(1, updatedOrder.getCustomerId());
        assertEquals(PaymentMethod.CARD, updatedOrder.getPaymentMethod());
        assertEquals(OrderStatus.PROCESSING, updatedOrder.getOrderStatus());
    }
}