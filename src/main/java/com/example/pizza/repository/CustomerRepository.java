package com.example.pizza.repository;

import com.example.pizza.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

   // List<Customer> findCustomerByStatusIs(Integer status);

}

