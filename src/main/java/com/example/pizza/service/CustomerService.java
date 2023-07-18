package com.example.pizza.service;

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
    void createNewCustomer(Customer customer);

    /**
     * Returns a list of all customers.
     *
     * @return a list of all customers
     */
    List<Customer> findAll();

    /**
     * Finds a customer by their ID.
     *
     * @param id the ID of the customer to find
     * @return the customer with the specified ID, or null if not found
     */
    Customer findById(Integer id);

    /**
     * Updates a customer with the specified ID.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer object
     * @return the updated customer
     */
    Customer update(Integer id, Customer customer);

    /**
     * Deletes a customer with the specified ID.
     *
     * @param id the ID of the customer to delete
     */
    void delete(Integer id);
}
