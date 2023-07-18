package com.example.pizza.service.impl;

import com.example.pizza.enam.PizzaStatus;
import com.example.pizza.entity.Order;
import com.example.pizza.entity.Pizza;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.service.OrderService;
import com.example.pizza.service.conventer.PizzaUpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PizzaServiceImplTest {
    @Mock
    PizzaRepository pizzaRepository;
    @Mock
    PizzaUpdateService pizzaUpdateService;
    @Mock
    OrderService orderService;
    @InjectMocks
    PizzaServiceImpl pizzaService;
    @Captor
    private ArgumentCaptor<Order> orderCapture;


    @Test
    void createNewPizza_ShouldSavePizza_ok() {
        // Arrange
        Pizza pizza = new Pizza();

        // Act
        pizzaService.createNewPizza(pizza);

        // Assert
        verify(pizzaRepository, times(1)).save(pizza);
    }

    @Test
    void findAll_ShouldReturnAllPizzas_ok() {
        // Arrange
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza());
        pizzas.add(new Pizza());

        when(pizzaRepository.findAll()).thenReturn(pizzas);

        // Act
        List<Pizza> result = pizzaService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void findById_ExistingId_ShouldReturnPizza_ok() {
        // Arrange
        Integer id = 1;
        Pizza pizza = new Pizza();
        pizza.setId(id);

        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));

        // Act
        Pizza result = pizzaService.findById(id);

        // Assert
        assertEquals(pizza, result);
    }
    @Test
    void findById_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;

        when(pizzaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> pizzaService.findById(id));
    }

    @Test
    void update_ExistingId_ShouldUpdateAndReturnPizza_ok() {
        // Arrange
        Integer id = 1;
        Pizza existingPizza = new Pizza();
        Pizza updatedPizza = new Pizza();

        when(pizzaRepository.findById(id)).thenReturn(Optional.of(existingPizza));
        when(pizzaUpdateService.convert(existingPizza, updatedPizza)).thenReturn(updatedPizza);
        when(pizzaRepository.save(updatedPizza)).thenReturn(updatedPizza);

        // Act
        Pizza result = pizzaService.update(id, updatedPizza);

        // Assert
        assertEquals(updatedPizza, result);
        verify(pizzaRepository, times(1)).save(updatedPizza);
    }
    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        Pizza updatedPizza = new Pizza();
        when(pizzaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> pizzaService.update(id, updatedPizza));
        verify(pizzaRepository, never()).save(updatedPizza);
    }


    @Test
    void delete_ShouldDeletePizza_ok() {
        // Arrange
        Integer id = 1;

        // Act
        pizzaService.delete(id);

        // Assert
        verify(pizzaRepository, times(1)).deleteById(id);
    }

    @Test
    void blockPizzaById_ExistingId_ShouldBlockPizza_ok() {
        // Arrange
        Integer id = 1;
        Pizza pizza = new Pizza();

        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));
        when(pizzaRepository.save(pizza)).thenReturn(pizza);

        // Act
        pizzaService.blockPizzaById(id);

        // Assert
        assertEquals(PizzaStatus.UNAVAILABLE, pizza.getStatus());
        verify(pizzaRepository, times(1)).save(pizza);
    }

    @Test
    void orderNewPizza_ShouldCreateNewOrderAndSavePizza_ok() {
        // Arrange
        Integer customerId = 1;
        Pizza pizza = new Pizza();

        when(pizzaRepository.save(pizza)).thenReturn(pizza);

        // Act
        pizzaService.orderNewPizza(customerId, pizza);

        // Assert
        verify(pizzaRepository, times(1)).save(pizza);
        verify(orderService).createNewOrder(any(Order.class));
        verify(orderService).createNewOrder(orderCapture.capture());
        Order order = orderCapture.getValue();
        assertEquals(order.getId(), pizza.getOrderId());
    }


}