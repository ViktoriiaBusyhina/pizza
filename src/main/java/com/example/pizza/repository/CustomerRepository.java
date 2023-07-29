package com.example.pizza.repository;

import com.example.pizza.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The CustomerRepository interface provides database operations for the Customer entity.
 * It extends the JpaRepository interface, providing generic CRUD methods for Customer entities.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

