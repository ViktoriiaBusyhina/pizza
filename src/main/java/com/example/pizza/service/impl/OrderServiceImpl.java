package com.example.pizza.service.impl;

import com.example.pizza.enam.StatusOrder;
import com.example.pizza.enam.StatusPizza;
import com.example.pizza.entity.Order;
import com.example.pizza.entity.Pizza;
import com.example.pizza.repository.OrderRepository;
import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.service.OrderService;
import com.example.pizza.service.conventer.OrderUpdateService;
import com.example.pizza.service.conventer.PizzaUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderUpdateService orderUpdateService;

    @Override
    public void createNewOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order findById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order update(Integer id, Order orderUpdate) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            Order updated = orderUpdateService.convert(existingOrder, orderUpdate);
            orderRepository.save(updated);
        }
        return orderOptional.orElse(null);


    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);

    }

    @Override
    public String checkOrderStatus(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return order.getStatusOrder().name();
        }
        return "no status";


}}

