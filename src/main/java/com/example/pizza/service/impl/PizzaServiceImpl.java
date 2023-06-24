package com.example.pizza.service.impl;

import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Pizza;
import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.service.PizzaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    @Override
    public void createNewPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        return pizzaOptional.orElse(null);
    }

    @Override
    @Transactional
    public Pizza update(Integer id, Pizza pizzaUpdate) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()) {
            Pizza pizza= pizzaOptional.get();
            Pizza existingPizza = pizzaOptional.get();
            Pizza updated = pizzaUpdateService.convert(existingPizza, pizzaUpdate);
            pizzaRepository.save(updated);
        }
        return pizzaOptional.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        pizzaRepository.deleteById(id);
    }


    }
