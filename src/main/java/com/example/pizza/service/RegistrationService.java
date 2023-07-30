package com.example.pizza.service;

import com.example.pizza.dto.CustomerRegistrationDto;
/**
 * Service interface for customer registration.
 * This interface provides a method to register a new customer with the provided registration details.
 */
public interface RegistrationService {

    /**
     * Registers a new customer with the provided registration details.
     *
     * @param customerRegistrationDto The DTO containing customer registration details.
     */
    void registerNewCustomer(CustomerRegistrationDto customerRegistrationDto);
}
