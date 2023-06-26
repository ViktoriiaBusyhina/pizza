package com.example.pizza.service.impl;

import com.example.pizza.entity.Customer;
import com.example.pizza.repository.CustomerRepository;
import com.example.pizza.service.CustomerService;
import com.example.pizza.service.conventer.CustomerUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerUpdateService customerUpdateService;

    @Override
    public void createNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer findById(UUID uuid) {
        Optional<Customer> customerOptional = customerRepository.findById(uuid);
        return customerOptional.orElse(null);
    }

    @Override
    @Transactional
    public Customer update(UUID uuid, Customer customerUpdate) {
        Optional<Customer> customerOptional = customerRepository.findById(uuid);
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            Customer updated = customerUpdateService.convert(existingCustomer, customerUpdate);
            customerRepository.save(updated);
        }
        return customerOptional.orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
        customerRepository.deleteById(uuid);
    }
}
