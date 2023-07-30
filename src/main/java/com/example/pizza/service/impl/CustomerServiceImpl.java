package com.example.pizza.service.impl;

import com.example.pizza.dto.CustomerDto;
import com.example.pizza.dto.mapper.CustomerDtoMapper;
import com.example.pizza.entity.Cafe;
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
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    @Transactional
    public void createNewCustomer(CustomerDto customerDto) {
        Customer customer = customerDtoMapper.dtoToEntity(customerDto);
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerDtoMapper::entityToDto)
                .toList();
    }

    @Override
    @Transactional
    public CustomerDto findById(Integer id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        return customerDtoMapper.entityToDto(existingCustomer);
    }

    @Override
    @Transactional
    public void update(Integer id, CustomerDto customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        Customer customerUpdate = customerDtoMapper.dtoToEntity(customer);
        Customer updated = customerUpdateService.convert(existingCustomer, customerUpdate);
        customerRepository.save(updated);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
