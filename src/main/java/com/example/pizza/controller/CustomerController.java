package com.example.pizza.controller;

import com.example.pizza.entity.Customer;
import com.example.pizza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<Customer> findCustomerByUuid(@PathVariable Integer id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/customer/update/{uuid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer customerUpdate = customerService.update(id, customer);
        return ResponseEntity.ok(customerUpdate);
    }

    @DeleteMapping(value = "/customer/delete/{uuid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
