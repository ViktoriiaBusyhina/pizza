package com.example.pizza.service.conventer;

import com.example.pizza.entity.Pizza;
import org.springframework.stereotype.Service;

@Service
public class PizzaUpdateService {
    public Pizza convert(Pizza pizza, Pizza pizzaUpdate) {
        if (pizzaUpdate.getPizzaName() != null) {
            pizza.setPizzaName(pizzaUpdate.getPizzaName());
        }
        if (pizzaUpdate.getSize() != null) {
            pizza.setSize(pizzaUpdate.getSize());
        }

        return pizza;

    }
}
