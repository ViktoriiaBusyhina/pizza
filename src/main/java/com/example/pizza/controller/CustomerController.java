package com.example.pizza.controller;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The CustomerController class handles customer-related API endpoints.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @PostMapping(value = "/new-customer")
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customer) {
        customerService.createNewCustomer(customer);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a ResponseEntity containing the list of customers or indicating no content
     */
    @GetMapping(value = "/customer/find/all")
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<CustomerDto> customerList = customerService.findAll();
        if (customerList != null && !customerList.isEmpty()) {
            return ResponseEntity.ok(customerList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return a ResponseEntity containing the retrieved customer
     */
    @GetMapping(value = "/customer/find/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Integer id) {
        CustomerDto customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    /**
     * Updates a customer with the specified ID.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer object
     * @return a ResponseEntity with the updated customer or HTTP status 404 if the customer is not found
     */
    @PutMapping(value = "/customer/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customer) {
        customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * Deletes a customer with the specified ID.
     *
     * @param id the ID of the customer to delete
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @DeleteMapping(value = "/customer/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
