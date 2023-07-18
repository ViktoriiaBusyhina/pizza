package com.example.pizza.service.impl;

import com.example.pizza.entity.Customer;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.CustomerRepository;
import com.example.pizza.service.conventer.CustomerUpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerUpdateService customerUpdateService;
    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void createNewCustomer_ok() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customerService.createNewCustomer(customer);

        // Assert
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void findAll_ShouldReturnAllCustomers_ok() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.findAll();

        // Assert
        assertEquals(2, result.size());
    }


    @Test
    void findById_ExistingId_ShouldReturnCustomer_ok() {
        // Arrange
        Integer id = 1;
        Customer customer = new Customer();
        customer.setId(id);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerService.findById(id);

        // Assert
        assertEquals(customer, result);
    }
    @Test
    void findById_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> customerService.findById(id));
    }

    @Test
    void update_ExistingId_ShouldUpdateAndReturnCustomer_ok() {
        // Arrange
        Integer id = 1;
        Customer existingCustomer = new Customer();
        Customer updatedCustomer = new Customer();

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
        when(customerUpdateService.convert(existingCustomer, updatedCustomer)).thenReturn(updatedCustomer);
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        // Act
        Customer result = customerService.update(id, updatedCustomer);

        // Assert
        assertEquals(updatedCustomer, result);
        verify(customerRepository, times(1)).save(updatedCustomer);
    }

    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        Customer updatedCustomer = new Customer();

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> customerService.update(id, updatedCustomer));
        verify(customerRepository, never()).save(updatedCustomer);
    }


    @Test
    void delete_ShouldDeleteCustomer_ok() {
        // Arrange
        Integer id = 1;

        // Act
        customerService.delete(id);

        // Assert
        verify(customerRepository, times(1)).deleteById(id);
    }
}