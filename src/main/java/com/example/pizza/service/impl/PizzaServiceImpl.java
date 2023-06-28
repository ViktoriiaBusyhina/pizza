package com.example.pizza.service.impl;

import com.example.pizza.enam.StatusPizza;
import com.example.pizza.entity.Pizza;
import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.service.PizzaService;
import com.example.pizza.service.conventer.PizzaUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaUpdateService pizzaUpdateService;

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
            Pizza existingPizza = pizzaOptional.get();
            Pizza updated = pizzaUpdateService.convert(existingPizza, pizzaUpdate);
            pizzaRepository.save(updated);
        }
        return pizzaOptional.orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pizzaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void blockPizzaById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()) {
            Pizza pizza = pizzaOptional.get();
            pizza.setStatus(StatusPizza.valueOf(String.valueOf(StatusPizza.UNAVAILABLE)));
            pizzaRepository.save(pizza);
        }

    }


}
