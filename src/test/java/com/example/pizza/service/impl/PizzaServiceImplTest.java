package com.example.pizza.service.impl;

import com.example.pizza.dto.OrderDto;
import com.example.pizza.dto.PizzaDto;
import com.example.pizza.dto.mapper.PizzaDtoMapper;
import com.example.pizza.enam.PizzaStatus;
import com.example.pizza.entity.Order;
import com.example.pizza.entity.Pizza;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.service.OrderService;
import com.example.pizza.service.conventer.PizzaUpdateService;
import org.junit.jupiter.api.BeforeEach;
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
    PizzaDtoMapper pizzaDtoMapper;

    @Mock
    OrderService orderService;

    @InjectMocks
    PizzaServiceImpl pizzaService;

    PizzaDto pizzaDto;
    Pizza pizza;

    @Captor
    private ArgumentCaptor<OrderDto> orderCapture;

    @BeforeEach
    void setUp() {
        pizzaDto = new PizzaDto();
        pizza = new Pizza();
    }

    @Test
    void createNewPizza_ShouldSavePizza_ok() {
        // Arrange
        when(pizzaDtoMapper.dtoToEntity(pizzaDto)).thenReturn(pizza);

        // Act
        pizzaService.createNewPizza(pizzaDto);

        // Assert
        verify(pizzaRepository, times(1)).save(pizza);
    }

    @Test
    void findAll_ShouldReturnAllPizzas_ok() {
        // Arrange
        List<Pizza> pizzas = List.of(pizza);

        when(pizzaRepository.findAll()).thenReturn(pizzas);
        when(pizzaDtoMapper.entityToDto(pizza)).thenReturn(pizzaDto);

        // Act
        List<PizzaDto> result = pizzaService.findAll();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void findById_ExistingId_ShouldReturnPizza_ok() {
        // Arrange
        Integer id = 1;
        pizza.setId(id);

        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));
        when(pizzaDtoMapper.entityToDto(pizza)).thenReturn(pizzaDto);

        // Act
        PizzaDto result = pizzaService.findById(id);

        // Assert
        assertEquals(pizzaDto, result);
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
        when(pizzaDtoMapper.dtoToEntity(pizzaDto)).thenReturn(updatedPizza);
        when(pizzaUpdateService.convert(existingPizza, updatedPizza)).thenReturn(updatedPizza);
        when(pizzaRepository.save(updatedPizza)).thenReturn(updatedPizza);

        // Act
        pizzaService.update(id, pizzaDto);

        // Assert
        verify(pizzaRepository, times(1)).save(updatedPizza);
    }

    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        Pizza updatedPizza = new Pizza();

        when(pizzaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> pizzaService.update(id, pizzaDto));
        verify(pizzaRepository, never()).save(any(Pizza.class));
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
        when(pizzaDtoMapper.dtoToEntity(pizzaDto)).thenReturn(pizza);

        // Act
        pizzaService.orderNewPizza(customerId, pizzaDto);

        // Assert
        verify(pizzaRepository, times(1)).save(pizza);
        verify(orderService).createNewOrder(orderCapture.capture());
        OrderDto order = orderCapture.getValue();
    }

}