package com.example.pizza.dto.mapper;

import com.example.pizza.dto.OrderDto;
import com.example.pizza.dto.PizzaDto;
import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.entity.Order;
import com.example.pizza.entity.Pizza;


public class OrderDtoMapper implements DtoMapper<Order, OrderDto> {
    public Order dtoToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setCafeId(orderDto.getCafeId());
        order.setPaymentMethod(PaymentMethod.valueOf(orderDto.getPaymentMethod()));
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        return order;
    }

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setCafeId(order.getCafeId());
        orderDto.setPaymentMethod(String.valueOf(order.getPaymentMethod()));
        orderDto.setOrderStatus(String.valueOf(order.getOrderStatus()));
        orderDto.setOrderStatus(String.valueOf(order.getOrderStatus()));
        return  orderDto;
    }
}
