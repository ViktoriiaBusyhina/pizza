package com.example.pizza.controller;

import com.example.pizza.entity.Customer;
import com.example.pizza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/new-customer")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        customerService.createNewCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/customer/find/all")
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customerList = customerService.findAll();
        if (customerList != null && !customerList.isEmpty()) {
            return ResponseEntity.ok(customerList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/customer/find/{uuid}")
    public ResponseEntity<Customer> findCustomerByUuid(@PathVariable UUID uuid) {
        Customer customer = customerService.findById(uuid);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/customer/update/{uuid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID uuid, @RequestBody Customer customer) {
        Customer customerUpdate = customerService.update(uuid, customer);
        return ResponseEntity.ok(customerUpdate);
    }

    @DeleteMapping(value = "/customer/delete/{uuid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID uuid) {
        customerService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
