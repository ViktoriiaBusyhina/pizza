package com.example.pizza.service;

import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Pizza;

import java.util.List;
import java.util.UUID;

public interface PizzaService {
    void createNewPizza(Pizza pizza);

    List<Pizza> findAll();
    Pizza findById(Integer id);

    Pizza update(Integer id, Pizza  pizza);

    void delete(Integer id);
}
