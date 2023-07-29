package com.example.pizza.controller;

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
/**
 * The CustomerControllerTest class is a unit test class for CustomerController.
 * It tests the methods in CustomerController for handling customer-related operations.
 */
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    /**
     * Tests the createNewCustomer method in CustomerController.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also verifies that the createNewCustomer method in CustomerService is called once with the specified customer.
     */
    @Test
    void createNewCustomer_ShouldReturnOk_ok() {
        // Arrange
        Customer customer = new Customer();

        // Act
        ResponseEntity<Customer> response = customerController.createNewCustomer(customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).createNewCustomer(customer);
    }

    /**
     * Tests the findAllCustomers method in CustomerController when customers are present.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the list of customers returned by CustomerService.
     * It verifies that the findAll method in CustomerService is called once.
     */
    @Test
    void findAllCustomers_WithCustomers_ShouldReturnOkWithCustomers_ok() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());

        when(customerService.findAll()).thenReturn(customers);

        // Act
        ResponseEntity<List<Customer>> response = customerController.findAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
        verify(customerService, times(1)).findAll();
    }

    /**
     * Tests the findAllCustomers method in CustomerController when no customers are present.
     * It asserts that a ResponseEntity with status code HttpStatus.NO_CONTENT is returned.
     * It verifies that the findAll method in CustomerService is called once.
     */
    @Test
    void findAllCustomers_WithNoCustomers_ShouldReturnNoContent_ok() {
        // Arrange
        when(customerService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<Customer>> response = customerController.findAllCustomers();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).findAll();
    }

    /**
     * Tests the findCustomerById method in CustomerController for an existing customer id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the customer returned by CustomerService.
     * It verifies that the findById method in CustomerService is called once with the specified id.
     */
    @Test
    void findCustomerByUuid_ExistingId_ShouldReturnOkWithCustomer_ok() {
        // Arrange
        Integer id = 1;
        Customer customer = new Customer();
        customer.setId(id);

        when(customerService.findById(id)).thenReturn(customer);

        // Act
        ResponseEntity<Customer> response = customerController.findCustomerById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).findById(id);
    }

    /**
     * Tests the updateCustomer method in CustomerController for an existing customer id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It also asserts that the response body contains the updated customer returned by CustomerService.
     * It verifies that the update method in CustomerService is called once with the specified id and customer.
     */
    @Test
    void updateCustomer_ExistingId_ShouldReturnOkWithUpdatedCustomer_ok() {
        // Arrange
        Integer id = 1;
        Customer customer = new Customer();
        customer.setId(id);

        when(customerService.update(id, customer)).thenReturn(customer);

        // Act
        ResponseEntity<Customer> response = customerController.updateCustomer(id, customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).update(id, customer);
    }

    /**
     * Tests the updateCustomer method in CustomerController for a non-existing customer id.
     * It asserts that a ResponseEntity with status code HttpStatus.NOT_FOUND is returned.
     * It verifies that the update method in CustomerService is called once with the specified id and customer.
     */
    @Test
    void updateCustomer_NonExistingId_ShouldReturnNotFound_ok() {
        // Arrange
        Integer id = 1;
        Customer customer = new Customer();

        when(customerService.update(id, customer)).thenReturn(null);

        // Act
        ResponseEntity<Customer> response = customerController.updateCustomer(id, customer);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(customerService, times(1)).update(id, customer);
    }

    /**
     * Tests the deleteCustomer method in CustomerController for an existing customer id.
     * It asserts that a ResponseEntity with status code HttpStatus.OK is returned.
     * It verifies that the delete method in CustomerService is called once with the specified id.
     */
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