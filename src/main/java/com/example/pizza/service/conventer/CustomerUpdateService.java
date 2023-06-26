package com.example.pizza.service.conventer;

import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdateService {
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

