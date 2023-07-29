package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Component;
/**
 * The CustomerDtoMapper class is a component that maps between Customer and CustomerDto objects.
 */
@Component
public class CustomerDtoMapper implements DtoMapper<Customer, CustomerDto> {

    /**
     * Converts a CustomerDto object to a Customer entity.
     *
     * @param customerDto the CustomerDto object to convert
     * @return the converted Customer entity
     */
    public Customer dtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setPaymentMethod(PaymentMethod.valueOf(customerDto.getPaymentMethod()));
        customer.setBlocked(Boolean.parseBoolean(customerDto.getIsBlocked()));
        return customer;
    }

    /**
     * Converts a Customer entity to a CustomerDto object.
     *
     * @param customer the Customer entity to convert
     * @return the converted CustomerDto object
     */
    public CustomerDto entityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPaymentMethod(String.valueOf(customer.getPaymentMethod()));
        customerDto.setIsBlocked(String.valueOf(customer.isBlocked()));
        return customerDto;
    }
}