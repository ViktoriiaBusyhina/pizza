package com.example.pizza.dto.mapper;

import com.example.pizza.dto.OrderDto;
import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.PaymentStatus;
import com.example.pizza.entity.Order;
import org.springframework.stereotype.Component;


/**
 * The OrderDtoMapper class is a component that maps between Order and OrderDto objects.
 */
@Component
public class OrderDtoMapper implements DtoMapper<Order, OrderDto> {

    /**
     * Converts an OrderDto object to an Order entity.
     *
     * @param orderDto the OrderDto object to convert
     * @return the converted Order entity
     */
    public Order dtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setCafeId(orderDto.getCafeId());
        order.setPaymentMethod(PaymentMethod.valueOf(orderDto.getPaymentMethod()));
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        order.setPaymentStatus(PaymentStatus.valueOf(orderDto.getPaymentStatus()));
        return order;
    }

    /**
     * Converts an Order entity to an OrderDto object.
     *
     * @param order the Order entity to convert
     * @return the converted OrderDto object
     */
    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setCafeId(order.getCafeId());
        orderDto.setPaymentMethod(String.valueOf(order.getPaymentMethod()));
        orderDto.setOrderStatus(String.valueOf(order.getOrderStatus()));
        orderDto.setPaymentStatus(String.valueOf(order.getPaymentStatus()));
        return orderDto;
    }
}
