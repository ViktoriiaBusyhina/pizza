package com.example.pizza.service.impl;

import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.PaymentStatus;
import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Order;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.exception.OrderNotAllowedException;
import com.example.pizza.repository.OrderRepository;
import com.example.pizza.service.CustomerService;
import com.example.pizza.service.conventer.OrderUpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.pizza.enam.PaymentStatus.SUCCESSFUL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderUpdateService orderUpdateService;
    @Mock
    CustomerService customerService;
    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void createNewOrder_ValidCustomerAndSuccessfulPayment_ShouldSaveOrder_ok() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        Customer customer = new Customer();
        customer.setBlocked(false);
        customer.setPaymentMethod(PaymentMethod.CARD);
        order.setPaymentStatus(SUCCESSFUL);
        customer.setName("Jan");
        customer.setAddress("ciudg");
        customer.setPhone("3432543");
        customer.setEmail("dhcfgsivg");

        when(customerService.findById(1)).thenReturn(customer);
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        orderService.createNewOrder(order);

        // Assert
        assertEquals(OrderStatus.PROCESSING, order.getOrderStatus());
        verify(orderRepository, times(1)).save(order);
    }
    @Test
    void createNewOrder_InvalidCustomer_ShouldThrowIllegalArgumentException_ok() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        Customer customer = new Customer();

        when(customerService.findById(1)).thenReturn(customer);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> orderService.createNewOrder(order));
        verify(orderRepository, never()).save(order);
    }
    @Test
    void createNewOrder_OrderNotAllowed_ShouldThrowOrderNotAllowedException_ok() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        Customer customer = new Customer();
        customer.setBlocked(true);
        customer.setPaymentMethod(PaymentMethod.CARD);
        order.setPaymentStatus(SUCCESSFUL);
        customer.setName("Jan");
        customer.setAddress("ciudg");
        customer.setPhone("3432543");
        customer.setEmail("dhcfgsivg");

        when(customerService.findById(1)).thenReturn(customer);

        // Act & Assert
        assertThrows(OrderNotAllowedException.class, () -> orderService.createNewOrder(order));
        verify(orderRepository, never()).save(order);
    }

    @Test
    void createNewOrder_FailedPayment_ShouldNotSaveOrder_ok() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setPaymentStatus(PaymentStatus.FAILED);
        Customer customer = new Customer();
        customer.setBlocked(false);
        customer.setPaymentMethod(PaymentMethod.CARD);
        customer.setName("Jan");
        customer.setAddress("ciudg");
        customer.setPhone("3432543");
        customer.setEmail("dhcfgsivg");

        when(customerService.findById(1)).thenReturn(customer);

        // Act & Assert
        assertThrows(OrderNotAllowedException.class, () -> orderService.createNewOrder(order));
        verify(orderRepository, never()).save(order);
    }


    @Test
    void findById_ExistingId_ShouldReturnOrder_ok() {
        // Arrange
        Integer id = 1;
        Order order = new Order();
        order.setId(id);

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        // Act
        Order result = orderService.findById(id);

        // Assert
        assertEquals(order, result);
    }
    @Test
    void findById_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;

        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> orderService.findById(id));
    }


    @Test
    void findAll_ShouldReturnAllOrders_ok() {
        // Arrange
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<Order> result = orderService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void update_ExistingId_ShouldUpdateAndReturnOrder_ok() {
        // Arrange
        Integer id = 1;
        Order existingOrder = new Order();
        Order updatedOrder = new Order();

        when(orderRepository.findById(id)).thenReturn(Optional.of(existingOrder));
        when(orderUpdateService.convert(existingOrder, updatedOrder)).thenReturn(updatedOrder);
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        // Act
        Order result = orderService.update(id, updatedOrder);

        // Assert
        assertEquals(updatedOrder, result);
        verify(orderRepository, times(1)).save(updatedOrder);
    }
    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        Order updatedOrder = new Order();

        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> orderService.update(id, updatedOrder));
        verify(orderRepository, never()).save(updatedOrder);
    }


    @Test
    void delete_ShouldDeleteOrder_ok() {
        // Arrange
        Integer id = 1;

        // Act
        orderService.delete(id);

        // Assert
        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    void checkOrderStatus_ExistingId_ShouldReturnOrderStatus_ok() {
        // Arrange
        Integer id = 1;
        Order order = new Order();
        order.setOrderStatus(OrderStatus.PROCESSING);

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        // Act
        String result = orderService.checkOrderStatus(id);

        // Assert
        assertEquals(OrderStatus.PROCESSING.name(), result);
    }
    @Test
    void checkOrderStatus_NonExistingId_ShouldReturnNoStatus_ok() {
        // Arrange
        Integer id = 1;

        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        String result = orderService.checkOrderStatus(id);

        // Assert
        assertEquals("no status", result);
    }
}