package com.example.pizza.repository;

import com.example.pizza.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The OrderRepository interface provides database operations for the Order entity.
 * It extends the JpaRepository interface, providing generic CRUD methods for Order entities.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
