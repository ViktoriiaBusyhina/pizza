package com.example.pizza.repository;

import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    //List<Pizza> findPizzaByStatusIs(Integer status);

}
