package com.example.pizza.service;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.entity.Customer;

import java.util.List;

/**
 * The CustomerService interface provides methods for managing customers.
 */
public interface CustomerService {

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     */
    void createNewCustomer(CustomerDto customer);

    /**
     * Returns a list of all customers.
     *
     * @return a list of all customers
     */
    List<CustomerDto> findAll();

    /**
     * Finds a customer by their ID.
     *
     * @param id the ID of the customer to find
     * @return the customer with the specified ID, or null if not found
     */
    CustomerDto findById(Integer id);

    /**
     * Updates a customer with the specified ID.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer object
     * @return the updated customer
     */
    void update(Integer id, CustomerDto customer);

    /**
     * Deletes a customer with the specified ID.
     *
     * @param id the ID of the customer to delete
     */
    void delete(Integer id);
}
