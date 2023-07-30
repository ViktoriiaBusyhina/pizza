package com.example.pizza.service;

import com.example.pizza.dto.OrderDto;

import java.util.List;

/**
 * The OrderService interface provides methods for managing orders.
 */
public interface OrderService {

    /**
     * Creates a new order.
     *
     * @param order the order to create
     */
    void createNewOrder(OrderDto order);

    /**
     * Finds an order by its ID.
     *
     * @param id the ID of the order to find
     * @return the order with the specified ID, or null if not found
     */
    OrderDto findById(Integer id);

    /**
     * Returns a list of all orders.
     *
     * @return a list of all orders
     */
    List<OrderDto> findAll();

    /**
     * Updates an order with the specified ID.
     *
     * @param id    the ID of the order to update
     * @param order the updated order object
     * @return the updated order
     */
    void update(Integer id, OrderDto order);

    /**
     * Deletes an order with the specified ID.
     *
     * @param id the ID of the order to delete
     */
    void delete(Integer id);

    /**
     * Checks the status of an order with the specified ID.
     *
     * @param id the ID of the order to check
     * @return the status of the order as a string
     */
    String checkOrderStatus(Integer id);
}
