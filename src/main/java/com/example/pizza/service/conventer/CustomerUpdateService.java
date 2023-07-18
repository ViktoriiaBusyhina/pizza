package com.example.pizza.service.conventer;

import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Service;

/**
 * The CustomerUpdateService class provides a method to update a customer based on the changes specified in another customer object.
 */
@Service
public class CustomerUpdateService {

    /**
     * Updates the provided customer based on the changes specified in the customerUpdate object.
     *
     * @param customer         the original customer object
     * @param customerUpdate   the customer object containing the updates
     * @return the updated customer
     */
    public Customer convert(Customer customer, Customer customerUpdate) {
        if (customerUpdate.getName() != null) {
            customer.setName(customerUpdate.getName());
        }
        if (customerUpdate.getEmail() != null) {
            customer.setEmail(customerUpdate.getEmail());
        }
        if (customerUpdate.getAddress() != null) {
            customer.setAddress(customerUpdate.getAddress());
        }
        if (customerUpdate.getPhone() != null) {
            customer.setPhone(customerUpdate.getPhone());
        }
        return customer;
    }
}


