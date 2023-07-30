package com.example.pizza.dto.mapper;

import com.example.pizza.dto.OrderDto;
import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.PaymentStatus;
import com.example.pizza.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderDtoMapperTest {

    private final OrderDtoMapper orderDtoMapper = new OrderDtoMapper();

    @Test
    void dtoToEntity_ShouldMapDtoToEntity() {
        // Arrange
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(2);
        orderDto.setCafeId(3);
        orderDto.setPaymentMethod("CARD");
        orderDto.setOrderStatus("PROCESSING");
        orderDto.setPaymentStatus("SUCCESSFUL");

        // Act
        Order result = orderDtoMapper.dtoToEntity(orderDto);

        // Assert
        assertEquals(orderDto.getCustomerId(), result.getCustomerId());
        assertEquals(orderDto.getCafeId(), result.getCafeId());
        assertEquals(PaymentMethod.CARD, result.getPaymentMethod());
        assertEquals(OrderStatus.PROCESSING, result.getOrderStatus());
        assertEquals(PaymentStatus.SUCCESSFUL, result.getPaymentStatus());
    }

    @Test
    void entityToDto_ShouldMapEntityToDto() {
        // Arrange
        Order order = new Order();
        order.setId(1);
        order.setCustomerId(2);
        order.setCafeId(3);
        order.setPaymentMethod(PaymentMethod.CASH_TO_THE_COURIER);
        order.setOrderStatus(OrderStatus.CANCELED);

        // Act
        OrderDto result = orderDtoMapper.entityToDto(order);

        // Assert
        assertEquals(order.getCustomerId(), result.getCustomerId());
        assertEquals(order.getCafeId(), result.getCafeId());
        assertEquals(String.valueOf(order.getPaymentMethod()), result.getPaymentMethod());
        assertEquals(String.valueOf(order.getOrderStatus()), result.getOrderStatus());
    }
}