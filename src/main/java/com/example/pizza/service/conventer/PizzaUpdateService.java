package com.example.pizza.service.conventer;

import com.example.pizza.entity.Pizza;
import org.springframework.stereotype.Service;


/**
 * The PizzaUpdateService class provides a method to update a pizza based on the changes specified in another pizza object.
 */
@Service
public class PizzaUpdateService {

    /**
     * Updates the provided pizza based on the changes specified in the pizzaUpdate object.
     *
     * @param pizza        the original pizza object
     * @param pizzaUpdate  the pizza object containing the updates
     * @return the updated pizza
     */
    public Pizza convert(Pizza pizza, Pizza pizzaUpdate) {
        if (pizzaUpdate.getPizzaName() != null) {
            pizza.setPizzaName(pizzaUpdate.getPizzaName());
        }
        if (pizzaUpdate.getSize() != null) {
            pizza.setSize(pizzaUpdate.getSize());
        }
        if (pizzaUpdate.getStatus() != null) {
            pizza.setStatus(pizzaUpdate.getStatus());
        }
        if (pizzaUpdate.getOrderId() != null) {
            pizza.setOrderId(pizzaUpdate.getOrderId());
        }
        if (pizzaUpdate.getCafeId() != null) {
            pizza.setCafeId(pizzaUpdate.getCafeId());
        }
        if (pizzaUpdate.getPrice() != null) {
            pizza.setPrice(pizzaUpdate.getPrice());
        }
        return pizza;
    }
}

