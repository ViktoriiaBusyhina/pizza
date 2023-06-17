package com.example.pizza.service;

import com.example.pizza.entity.Cafe;
import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Pizza;

import java.util.List;
import java.util.UUID;

public interface CafeService {
     void createNewCafe(Cafe cafe);

    List<Cafe> findAll();
    Cafe findById(UUID uuid);

    Cafe update(UUID uuid, Cafe cafe);

    void delete(UUID uuid);
}
