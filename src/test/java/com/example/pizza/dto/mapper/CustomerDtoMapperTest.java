package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerDtoMapperTest {

   CustomerDtoMapper customerDtoMapper = new CustomerDtoMapper();

    @Test
    void dtoToEntity_ShouldMapDtoToEntity() {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1);
        customerDto.setName("John Doe");
        customerDto.setAddress("123 Main St");
        customerDto.setPhone("555-1234");
        customerDto.setEmail("john@example.com");
        customerDto.setPaymentMethod("CREDIT_CARD");
        customerDto.setIsBlocked("false");

        // Act
        Customer result = customerDtoMapper.dtoToEntity(customerDto);

        // Assert
        assertEquals(customerDto.getId(), result.getId());
        assertEquals(customerDto.getName(), result.getName());
        assertEquals(customerDto.getAddress(), result.getAddress());
        assertEquals(customerDto.getPhone(), result.getPhone());
        assertEquals(customerDto.getEmail(), result.getEmail());
        assertEquals(PaymentMethod.CARD, result.getPaymentMethod());
        assertEquals(false, result.isBlocked());
    }

    @Test
    void entityToDto_ShouldMapEntityToDto() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setPhone("555-1234");
        customer.setEmail("john@example.com");
        customer.setPaymentMethod(PaymentMethod.CASH_TO_THE_COURIER);
        customer.setBlocked(true);

        // Act
        CustomerDto result = customerDtoMapper.entityToDto(customer);

        // Assert
        assertEquals(customer.getId(), result.getId());
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getAddress(), result.getAddress());
        assertEquals(customer.getPhone(), result.getPhone());
        assertEquals(customer.getEmail(), result.getEmail());
        assertEquals(String.valueOf(customer.getPaymentMethod()), result.getPaymentMethod());
        assertEquals(String.valueOf(customer.isBlocked()), result.getIsBlocked());
    }
}
