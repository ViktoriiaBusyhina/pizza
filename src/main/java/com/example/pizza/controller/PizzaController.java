package com.example.pizza.controller;

import com.example.pizza.dto.PizzaDto;
import com.example.pizza.service.PizzaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The PizzaController class handles pizza-related API endpoints.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    /**
     * Creates a new pizza.
     *
     * @param pizza the pizza to create
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @PostMapping(value = "/new-pizza")
    public ResponseEntity<PizzaDto> createNewPizza(@RequestBody PizzaDto pizza) {
        pizzaService.createNewPizza(pizza);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of all pizzas.
     *
     * @return a ResponseEntity containing the list of pizzas or indicating no content
     */
    @GetMapping(value = "/pizza/find/all")
    public ResponseEntity<List<PizzaDto>> findAllPizzas() {
        List<PizzaDto> pizzaList = pizzaService.findAll();
        if (pizzaList != null && !pizzaList.isEmpty()) {
            return ResponseEntity.ok(pizzaList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Retrieves a pizza by its ID.
     *
     * @param id the ID of the pizza to retrieve
     * @return a ResponseEntity containing the retrieved pizza
     */
    @GetMapping(value = "/pizza/find/{id}")
    public ResponseEntity<PizzaDto> findPizzaById(@PathVariable Integer id) {
        PizzaDto pizza = pizzaService.findById(id);
        return ResponseEntity.ok(pizza);
    }

    /**
     * Updates a pizza with the specified ID.
     *
     * @param id    the ID of the pizza to update
     * @param pizza the updated pizza object
     * @return a ResponseEntity with the updated pizza or HTTP status 404 if the pizza is not found
     */
    @PutMapping(value = "/pizza/update/{id}")
    public ResponseEntity<PizzaDto> updatePizza(@PathVariable Integer id, @RequestBody PizzaDto pizza) {
        pizzaService.update(id, pizza);
        return ResponseEntity.ok(pizza);
    }

    /**
     * Deletes a pizza with the specified ID.
     *
     * @param id the ID of the pizza to delete
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @DeleteMapping(value = "/pizza/delete/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable Integer id) {
        pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Blocks a pizza by its ID.
     *
     * @param id the ID of the pizza to block
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @PostMapping(value = "/pizza/block_pizza/{id}")
    public ResponseEntity<String> blockPizzaById(@PathVariable Integer id) {
        pizzaService.blockPizzaById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Places an order for a pizza with the specified ID.
     *
     * @param customerId the ID of the customer
     * @param pizza      the pizza to order
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @PostMapping(value = "/pizza/order/customer-id/{customerId}")
    public ResponseEntity<String> orderPizza(@PathVariable Integer customerId, @RequestBody PizzaDto pizza) {
        pizzaService.orderNewPizza(customerId, pizza);
        return ResponseEntity.ok().build();
    }
}

