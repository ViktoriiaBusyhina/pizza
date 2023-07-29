package com.example.pizza.controller;

import com.example.pizza.entity.Pizza;
import com.example.pizza.service.PizzaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PizzaControllerTest {
    @Mock
    PizzaService pizzaService;

    @InjectMocks
    private PizzaController pizzaController;


    @Test
    void createNewPizza_ShouldReturnOk_ok() {
        // Arrange
        Pizza pizza = new Pizza();

        // Act
        ResponseEntity<Pizza> response = pizzaController.createNewPizza(pizza);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pizzaService, times(1)).createNewPizza(pizza);
    }

    @Test
    void findAllPizzas_WithPizzas_ShouldReturnOkWithPizzas_ok() {
        // Arrange
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza());

        when(pizzaService.findAll()).thenReturn(pizzas);

        // Act
        ResponseEntity<List<Pizza>> response = pizzaController.findAllPizzas();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pizzas, response.getBody());
        verify(pizzaService, times(1)).findAll();
    }

    @Test
    void findAllPizzas_WithNoPizzas_ShouldReturnNoContent_ok() {
        // Arrange
        when(pizzaService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<Pizza>> response = pizzaController.findAllPizzas();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pizzaService, times(1)).findAll();
    }

    @Test
    void findPizzaById_ExistingId_ShouldReturnOkWithPizza_ok() {
        // Arrange
        Integer id = 1;
        Pizza pizza = new Pizza();
        pizza.setId(id);

        when(pizzaService.findById(id)).thenReturn(pizza);

        // Act
        ResponseEntity<Pizza> response = pizzaController.findPizzaById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pizza, response.getBody());
        verify(pizzaService, times(1)).findById(id);
    }


    @Test
    void updatePizza_ExistingId_ShouldReturnOkWithUpdatedPizza_ok() {
        // Arrange
        Integer id = 1;
        Pizza pizza = new Pizza();
        pizza.setId(id);

        when(pizzaService.update(id, pizza)).thenReturn(pizza);

        // Act
        ResponseEntity<Pizza> response = pizzaController.updatePizza(id, pizza);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pizza, response.getBody());
        verify(pizzaService, times(1)).update(id, pizza);
    }

    @Test
    void updatePizza_NonExistingId_ShouldReturnNotFound_ok() {
        // Arrange
        Integer id = 1;
        Pizza pizza = new Pizza();

        when(pizzaService.update(id, pizza)).thenReturn(null);

        // Act
        ResponseEntity<Pizza> response = pizzaController.updatePizza(id, pizza);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pizzaService, times(1)).update(id, pizza);
    }

    @Test
    void deletePizza_ExistingId_ShouldReturnOk_ok() {
        // Arrange
        Integer id = 1;

        // Act
        ResponseEntity<String> response = pizzaController.deletePizza(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pizzaService, times(1)).delete(id);
    }

    @Test
    void blockPizzaById_ExistingId_ShouldReturnOk_ok() {
        // Arrange
        Integer id = 1;

        // Act
        ResponseEntity<String> response = pizzaController.blockPizzaById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pizzaService, times(1)).blockPizzaById(id);
    }

    @Test
    void orderPizza_ShouldReturnOk_ok() {
        // Arrange
        Integer customerId = 1;
        Pizza pizza = new Pizza();

        // Act
        ResponseEntity<String> response = pizzaController.orderPizza(customerId, pizza);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pizzaService, times(1)).orderNewPizza(customerId, pizza);
    }

}