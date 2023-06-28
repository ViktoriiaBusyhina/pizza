package com.example.pizza.controller;

import com.example.pizza.entity.Order;
import com.example.pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DialectOverride;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/new-order")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        orderService.createNewOrder(order);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/order/find/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/order/find/all")
    public ResponseEntity<List<Order>> findAllOrders() {
        List<Order> orderList = orderService.findAll();
        if (orderList != null && !orderList.isEmpty()) {
            return ResponseEntity.ok(orderList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/order/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        Order orderUpdate = orderService.update(id, order);
        return ResponseEntity.ok(orderUpdate);
    }

    @DeleteMapping(value = "/order/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/order/checkOrderStatus/{id}")
    public ResponseEntity<String> checkOrderStatus(@PathVariable Integer id){
        String order = orderService.checkOrderStatus(id);
        return ResponseEntity.ok(order);

    }
}
