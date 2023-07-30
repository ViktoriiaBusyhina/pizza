package com.example.pizza.service.impl;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.dto.mapper.CustomerDtoMapper;
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
    @Mock
    CustomerDtoMapper customerDtoMapper;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void createNewCustomer_ok() {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        when(customerDtoMapper.dtoToEntity(customerDto)).thenReturn(new Customer());

        // Act
        customerService.createNewCustomer(customerDto);

        // Assert
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void findAll_ShouldReturnAllCustomers_ok() {
        // Arrange
        Customer customer = new Customer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerDtoMapper.entityToDto(customer)).thenReturn(new CustomerDto());

        // Act
        List<CustomerDto> result = customerService.findAll();

        // Assert
        verify(customerRepository).findAll();
        verify(customerDtoMapper).entityToDto(customer);
        assertEquals(1, result.size());
    }


    @Test
    void findById_ExistingId_ShouldReturnCustomer_ok() {
        // Arrange
        Integer id = 1;
        Customer customer = new Customer();
        customer.setId(id);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerDtoMapper.entityToDto(customer)).thenReturn(new CustomerDto());

        // Act
        CustomerDto result = customerService.findById(id);

        // Assert
        assertEquals(new CustomerDto(), result);
        verify(customerRepository).findById(id);
        verify(customerDtoMapper).entityToDto(customer);
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
        CustomerDto updatedCustomer = new CustomerDto();
        Customer mappedCustomer = new Customer();

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
        when(customerDtoMapper.dtoToEntity(updatedCustomer)).thenReturn(mappedCustomer);
        when(customerUpdateService.convert(existingCustomer, mappedCustomer)).thenReturn(mappedCustomer);

        // Act
        customerService.update(id, updatedCustomer);

        // Assert
        verify(customerRepository).findById(id);
        verify(customerDtoMapper).dtoToEntity(updatedCustomer);
        verify(customerRepository, times(1)).save(mappedCustomer);
    }

    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        CustomerDto updatedCustomer = new CustomerDto();

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> customerService.update(id, updatedCustomer));
        verify(customerRepository, never()).save(any(Customer.class));
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