package com.example.pizza.controller;

import com.example.pizza.dto.OrderDto;
import com.example.pizza.entity.Order;
import com.example.pizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The OrderController class handles order-related API endpoints.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates a new order.
     *
     * @param order the order to create
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @PostMapping(value = "/new-order")
    public ResponseEntity<OrderDto> createNewOrder(@RequestBody OrderDto order) {
        orderService.createNewOrder(order);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return a ResponseEntity containing the retrieved order
     */
    @GetMapping(value = "/order/find/{id}")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable Integer id) {
        OrderDto order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return a ResponseEntity containing the list of orders or indicating no content
     */
    @GetMapping(value = "/order/find/all")
    public ResponseEntity<List<OrderDto>> findAllOrders() {
        List<OrderDto> orderList = orderService.findAll();
        if (orderList != null && !orderList.isEmpty()) {
            return ResponseEntity.ok(orderList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Updates an order with the specified ID.
     *
     * @param id    the ID of the order to update
     * @param order the updated order object
     * @return a ResponseEntity with the updated order or HTTP status 404 if the order is not found
     */
    @PutMapping(value = "/order/update/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Integer id, @RequestBody OrderDto order) {
        orderService.update(id, order);
        return ResponseEntity.ok(order);
    }

    /**
     * Deletes an order with the specified ID.
     *
     * @param id the ID of the order to delete
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @DeleteMapping(value = "/order/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Checks the status of an order with the specified ID.
     *
     * @param id the ID of the order to check
     * @return a ResponseEntity containing the order status
     */
    @GetMapping(value = "/order/checkOrderStatus/{id}")
    public ResponseEntity<String> checkOrderStatus(@PathVariable Integer id) {
        String orderStatus = orderService.checkOrderStatus(id);
        return ResponseEntity.ok(orderStatus);
    }
}
