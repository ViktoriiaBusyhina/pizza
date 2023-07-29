package com.example.pizza.controller;

import com.example.pizza.entity.Order;
import com.example.pizza.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * The OrderControllerTest class is a unit test class for OrderController.
 * It tests the methods in OrderController for handling order-related operations.
 */
@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;
    /**
     * Tests the createNewOrder method in OrderController.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also verifies that the createNewOrder method in OrderService is called once with the specified order.
     */
    @Test
    void createNewOrder_ShouldReturnOk_ok() {
        // Arrange
        Order order = new Order();

        // Act
        ResponseEntity<Order> response = orderController.createNewOrder(order);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(orderService, times(1)).createNewOrder(order);
    }

    /**
     * Tests the findOrderById method in OrderController for an existing order id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the order returned by OrderService.
     * It verifies that the findById method in OrderService is called once with the specified id.
     */
    @Test
    void findOrderById_ExistingId_ShouldReturnOkWithOrder_ok() {
        // Arrange
        Integer id = 1;
        Order order = new Order();
        order.setId(id);

        when(orderService.findById(id)).thenReturn(order);

        // Act
        ResponseEntity<Order> response = orderController.findOrderById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).findById(id);
    }


    /**
     * Tests the findAllOrders method in OrderController when orders are present.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the list of orders returned by OrderService.
     * It verifies that the findAll method in OrderService is called once.
     */
    @Test
    void findAllOrders_WithOrders_ShouldReturnOkWithOrders_ok() {
        // Arrange
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());

        when(orderService.findAll()).thenReturn(orders);

        // Act
        ResponseEntity<List<Order>> response = orderController.findAllOrders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
        verify(orderService, times(1)).findAll();
    }

    /**
     * Tests the findAllOrders method in OrderController when no orders are present.
     * It asserts that a ResponseEntity with status code HttpStatus.NO_CONTENT is returned.
     * It verifies that the findAll method in OrderService is called once.
     */
    @Test
    void findAllOrders_WithNoOrders_ShouldReturnNoContent_ok() {
        // Arrange
        when(orderService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<Order>> response = orderController.findAllOrders();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).findAll();
    }
    /**
     * Tests the updateOrder method in OrderController for an existing order id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the updated order returned by OrderService.
     * It verifies that the update method in OrderService is called once with the specified id and order.
     */
    @Test
    void updateOrder_ExistingId_ShouldReturnOkWithUpdatedOrder_ok() {
        // Arrange
        Integer id = 1;
        Order order = new Order();
        order.setId(id);

        when(orderService.update(id, order)).thenReturn(order);

        // Act
        ResponseEntity<Order> response = orderController.updateOrder(id, order);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).update(id, order);
    }
    /**
     * Tests the updateOrder method in OrderController for a non-existing order id.
     * It asserts that a ResponseEntity with status code HttpStatus.NOT_FOUND is returned.
     * It verifies that the update method in OrderService is called once with the specified id and order.
     */
    @Test
    void updateOrder_NonExistingId_ShouldReturnNotFound_ok() {
        // Arrange
        Integer id = 1;
        Order order = new Order();

        when(orderService.update(id, order)).thenReturn(null);

        // Act
        ResponseEntity<Order> response = orderController.updateOrder(id, order);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(orderService, times(1)).update(id, order);
    }
    /**
     * Tests the deleteOrder method in OrderController for an existing order id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It verifies that the delete method in OrderService is called once with the specified id.
     */
    @Test
    void deleteOrder_ExistingId_ShouldReturnOk_ok() {
        // Arrange
        Integer id = 1;

        // Act
        ResponseEntity<String> response = orderController.deleteOrder(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(orderService, times(1)).delete(id);
    }
    /**
     * Tests the checkOrderStatus method in OrderController for an existing order id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the order status returned by OrderService.
     * It verifies that the checkOrderStatus method in OrderService is called once with the specified id.
     */
    @Test
    void checkOrderStatus_ExistingId_ShouldReturnOkWithOrderStatus_ok() {
        // Arrange
        Integer id = 1;
        String orderStatus = "PROCESSING";

        when(orderService.checkOrderStatus(id)).thenReturn(orderStatus);

        // Act
        ResponseEntity<String> response = orderController.checkOrderStatus(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderStatus, response.getBody());
        verify(orderService, times(1)).checkOrderStatus(id);
    }
}