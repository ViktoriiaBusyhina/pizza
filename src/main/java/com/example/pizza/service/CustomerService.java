package com.example.pizza.service;

import com.example.pizza.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void createNewCustomer(Customer customer);

    List<Customer> findAll();

    Customer findById(Integer id);

    Customer update(Integer id, Customer customer);

    void delete(Integer id);
}
