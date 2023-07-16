package com.example.pizza.service.impl;

import com.example.pizza.entity.Customer;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.CustomerRepository;
import com.example.pizza.service.CustomerService;
import com.example.pizza.service.conventer.CustomerUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Customer findById(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    @Transactional
    public Customer update(Integer id, Customer customerUpdate) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            Customer updated = customerUpdateService.convert(existingCustomer, customerUpdate);
            customerRepository.save(updated);
        }
        return customerOptional.orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
