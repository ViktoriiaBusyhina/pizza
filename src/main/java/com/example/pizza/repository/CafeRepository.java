package com.example.pizza.repository;

import com.example.pizza.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The CafeRepository interface provides database operations for the Cafe entity.
 * It extends the JpaRepository interface, providing generic CRUD methods for Cafe entities.
 */
@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {
}
