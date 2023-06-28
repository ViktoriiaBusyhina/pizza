package com.example.pizza.service;

import com.example.pizza.entity.Cafe;
import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void createNewOrder(Order order);

    Order findById(Integer id);

    List<Order> findAll();

    Order update(Integer id, Order order);

    void delete(Integer id);

    String checkOrderStatus(Integer id);

}
