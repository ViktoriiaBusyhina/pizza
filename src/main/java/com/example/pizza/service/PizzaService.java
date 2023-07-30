package com.example.pizza.service;


import com.example.pizza.dto.PizzaDto;
import com.example.pizza.entity.Pizza;

import java.util.List;

/**
 * The PizzaService interface provides methods for managing pizzas.
 */
public interface PizzaService {

    /**
     * Creates a new pizza.
     *
     * @param pizza the pizza to create
     */
    void createNewPizza(PizzaDto pizza);

    /**
     * Returns a list of all pizzas.
     *
     * @return a list of all pizzas
     */
    List<PizzaDto> findAll();

    /**
     * Finds a pizza by its ID.
     *
     * @param id the ID of the pizza to find
     * @return the pizza with the specified ID, or null if not found
     */
    PizzaDto findById(Integer id);

    /**
     * Updates a pizza with the specified ID.
     *
     * @param id    the ID of the pizza to update
     * @param pizza the updated pizza object
     * @return the updated pizza
     */
    void update(Integer id, PizzaDto pizza);

    /**
     * Deletes a pizza with the specified ID.
     *
     * @param id the ID of the pizza to delete
     */
    void delete(Integer id);

    /**
     * Blocks a pizza by its ID.
     *
     * @param id the ID of the pizza to block
     */
    void blockPizzaById(Integer id);

    void orderNewPizza(Integer customerId, PizzaDto pizza);
}
