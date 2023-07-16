package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper implements DtoMapper<Customer, CustomerDto>{
    public Customer dtoToEntity(CustomerDto customerDto){
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

    public CustomerDto entityToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPaymentMethod(String.valueOf(customer.getPaymentMethod()));
        customerDto.setIsBlocked(String.valueOf(customer.isBlocked()));
        return customerDto;
    }
}
