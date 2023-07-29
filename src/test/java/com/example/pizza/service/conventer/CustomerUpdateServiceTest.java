package com.example.pizza.service.conventer;

import com.example.pizza.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerUpdateServiceTest {
    @Test
    void testConvert_withUpdates_shouldUpdateCustomerObject_ok() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("Old Name");
        customer.setEmail("old@example.com");
        customer.setAddress("Old Address");
        customer.setPhone("Old Phone");

        Customer customerUpdate = new Customer();
        customerUpdate.setName("New Name");
        customerUpdate.setEmail("new@example.com");

        CustomerUpdateService customerUpdateService = new CustomerUpdateService();

        // Act
        Customer updatedCustomer = customerUpdateService.convert(customer, customerUpdate);

        // Assert
        assertEquals("New Name", updatedCustomer.getName());
        assertEquals("new@example.com", updatedCustomer.getEmail());
        assertEquals("Old Address", updatedCustomer.getAddress());
        assertEquals("Old Phone", updatedCustomer.getPhone());
    }

    @Test
    void testConvert_withNullUpdates_shouldNotUpdateCustomerObject_ok() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("Old Name");
        customer.setEmail("old@example.com");
        customer.setAddress("Old Address");
        customer.setPhone("Old Phone");

        Customer customerUpdate = new Customer(); // All attributes are null

        CustomerUpdateService customerUpdateService = new CustomerUpdateService();

        // Act
        Customer updatedCustomer = customerUpdateService.convert(customer, customerUpdate);

        // Assert
        assertEquals("Old Name", updatedCustomer.getName());
        assertEquals("old@example.com", updatedCustomer.getEmail());
        assertEquals("Old Address", updatedCustomer.getAddress());
        assertEquals("Old Phone", updatedCustomer.getPhone());
    }
}