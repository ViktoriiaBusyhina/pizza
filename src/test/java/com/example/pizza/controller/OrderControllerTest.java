package com.example.pizza.controller;

import com.example.pizza.dto.OrderDto;
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

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @Test
    void createNewOrder_ShouldReturnOk_ok() {
        // Arrange
        OrderDto order = new OrderDto();

        // Act
        ResponseEntity<OrderDto> response = orderController.createNewOrder(order);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(orderService, times(1)).createNewOrder(order);
    }


    @Test
    void findOrderById_ExistingId_ShouldReturnOkWithOrder_ok() {
        // Arrange
        Integer id = 1;
        OrderDto order = new OrderDto();
        order.setId(id);

        when(orderService.findById(id)).thenReturn(order);

        // Act
        ResponseEntity<OrderDto> response = orderController.findOrderById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).findById(id);
    }



    @Test
    void findAllOrders_WithOrders_ShouldReturnOkWithOrders_ok() {
        // Arrange
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto());

        when(orderService.findAll()).thenReturn(orders);

        // Act
        ResponseEntity<List<OrderDto>> response = orderController.findAllOrders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
        verify(orderService, times(1)).findAll();
    }


    @Test
    void findAllOrders_WithNoOrders_ShouldReturnNoContent_ok() {
        // Arrange
        when(orderService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<OrderDto>> response = orderController.findAllOrders();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).findAll();
    }

    @Test
    void updateOrder_ExistingId_ShouldReturnOkWithUpdatedOrder_ok() {
        // Arrange
        Integer id = 1;
        OrderDto order = new OrderDto();
        order.setId(id);

        // Act
        ResponseEntity<OrderDto> response = orderController.updateOrder(id, order);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).update(id, order);
    }

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