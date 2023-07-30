package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CustomerRegistrationDto;
import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert between CustomerRegistrationDto and Customer entities.
 */
@Component
public class CustomerRegistrationDtoMapper implements DtoMapper<Customer, CustomerRegistrationDto> {

    /**
     * Converts a CustomerRegistrationDto object to a Customer entity.
     *
     * @param customerDto The CustomerRegistrationDto object to convert.
     * @return The corresponding Customer entity.
     */
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

    /**
     * Converts a Customer entity to a CustomerRegistrationDto object.
     * This method is not implemented as it is not required for the registration process.
     *
     * @param entity The Customer entity to convert.
     * @return Always returns null as no mapping from entity to DTO is needed for registration.
     */
    @Override
    public CustomerRegistrationDto entityToDto(Customer entity) {
        return null;
    }
}
