package com.example.pizza.controller;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.entity.Customer;
import com.example.pizza.service.CustomerService;
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
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    void createNewCustomer_ShouldReturnOk_ok() {
        // Arrange
        CustomerDto customer = new CustomerDto();

        // Act
        ResponseEntity<CustomerDto> response = customerController.createNewCustomer(customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).createNewCustomer(customer);
    }

    @Test
    void findAllCustomers_WithCustomers_ShouldReturnOkWithCustomers_ok() {
        // Arrange
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(new CustomerDto());

        when(customerService.findAll()).thenReturn(customers);

        // Act
        ResponseEntity<List<CustomerDto>> response = customerController.findAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).findAll();
    }

    @Test
    void findAllCustomers_WithNoCustomers_ShouldReturnNoContent_ok() {
        // Arrange
        when(customerService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<CustomerDto>> response = customerController.findAllCustomers();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).findAll();
    }

    @Test
    void findCustomerByUuid_ExistingId_ShouldReturnOkWithCustomer_ok() {
        // Arrange
        Integer id = 1;
        CustomerDto customer = new CustomerDto();

        when(customerService.findById(id)).thenReturn(customer);

        // Act
        ResponseEntity<CustomerDto> response = customerController.findCustomerById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).findById(id);
    }

    @Test
    void updateCustomer_ExistingId_ShouldReturnOkWithUpdatedCustomer_ok() {
        // Arrange
        Integer id = 1;
        CustomerDto customer = new CustomerDto();

        // Act
        ResponseEntity<CustomerDto> response = customerController.updateCustomer(id, customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).update(id, customer);
    }

    @Test
    void deleteCustomer_ExistingId_ShouldReturnOk_ok() {
        // Arrange
        Integer id = 1;

        // Act
        ResponseEntity<String> response = customerController.deleteCustomer(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).delete(id);
    }
}