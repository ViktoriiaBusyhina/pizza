package com.example.pizza.repository;

import com.example.pizza.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The PizzaRepository interface provides database operations for the Pizza entity.
 * It extends the JpaRepository interface, providing generic CRUD methods for Pizza entities.
 */
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    // List<Pizza> findPizzaByStatusIs(Integer status);

}
