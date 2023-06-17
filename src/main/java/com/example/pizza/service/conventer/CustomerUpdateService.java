package com.example.pizza.service.conventer;

import com.example.pizza.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdateService {
    public Customer convert(Customer customer, Customer customerUpdate) {
        if (customerUpdate.getCustomerName() != null) {
            customer.setCustomerName(customerUpdate.getCustomerName());
        }
        if (customerUpdate.getCustomerEmail() != null) {
            customer.setCustomerEmail(customerUpdate.getCustomerEmail());
        }
        if (customerUpdate.getCustomerAddress() != null) {
            customer.setCustomerAddress(customerUpdate.getCustomerAddress());
        }
        if (customerUpdate.getCustomerPhone() != null) {
            customer.setCustomerPhone(customerUpdate.getCustomerPhone());
        }
        return customer;

    }
}

