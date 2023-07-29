package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CustomerRegistrationDto;
import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrationDtoMapper implements DtoMapper<Customer, CustomerRegistrationDto> {
    @Override
    public Customer dtoToEntity(CustomerRegistrationDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        return customer;
    }

    @Override
    public CustomerRegistrationDto entityToDto(Customer entity) {
        return null;
    }
}
