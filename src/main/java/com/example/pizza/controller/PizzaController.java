package com.example.pizza.controller;

import com.example.pizza.entity.Pizza;
import com.example.pizza.service.PizzaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping(value = "/new-pizza")
    public ResponseEntity<Pizza> createNewPizza(@RequestBody Pizza pizza) {
        pizzaService.createNewPizza(pizza);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/pizza/find/all")
    public ResponseEntity<List<Pizza>> findAllPizzas() {
        List<Pizza> pizzaList = pizzaService.findAll();
        if (pizzaList != null && !pizzaList.isEmpty()) {
            return ResponseEntity.ok(pizzaList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/pizza/find/{id}")
    public ResponseEntity<Pizza> findPizzaById(@PathVariable Integer id) {
        Pizza pizza = pizzaService.findById(id);
        return ResponseEntity.ok(pizza);
    }

    @PutMapping(value = "/pizza/update/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Integer id, @RequestBody Pizza pizza) {
        Pizza pizzaUpdate = pizzaService.update(id, pizza);
        return ResponseEntity.ok(pizzaUpdate);
    }

    @DeleteMapping(value = "/pizza/delete/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable Integer id) {
        pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/pizza/blocking/{id}")
    public ResponseEntity<String> blockingPizza(@PathVariable Integer id){
        pizzaService.blockingPizza(id);
        return ResponseEntity.ok().build();
    }


}
