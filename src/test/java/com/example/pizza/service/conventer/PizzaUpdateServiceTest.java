package com.example.pizza.service.conventer;

import com.example.pizza.enam.PizzaName;
import com.example.pizza.enam.PizzaSize;
import com.example.pizza.enam.PizzaStatus;
import com.example.pizza.entity.Pizza;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PizzaUpdateServiceTest {

    @Test
    void testConvert_withUpdates_shouldUpdatePizzaObject() {
        // Arrange
        Pizza pizza = new Pizza();
        pizza.setPizzaName(PizzaName.MARGHERITA);
        pizza.setSize(PizzaSize.KIDS);
        pizza.setStatus(PizzaStatus.READY_TO_ORDER);
        pizza.setOrderId(1);
        pizza.setCafeId(1);
        pizza.setPrice(BigDecimal.valueOf(15.0));

        Pizza pizzaUpdate = new Pizza();
        pizzaUpdate.setPizzaName(PizzaName.PEPPERONI);
        pizzaUpdate.setSize(PizzaSize.MEDIUM);
        pizzaUpdate.setStatus(PizzaStatus.UNAVAILABLE);
        pizzaUpdate.setOrderId(2);
        pizzaUpdate.setCafeId(2);
        pizzaUpdate.setPrice(BigDecimal.valueOf(15.0));

        PizzaUpdateService pizzaUpdateService = new PizzaUpdateService();

        // Act
        Pizza updatedPizza = pizzaUpdateService.convert(pizza, pizzaUpdate);

        // Assert
        assertEquals(PizzaName.PEPPERONI, updatedPizza.getPizzaName());
        assertEquals(PizzaSize.MEDIUM, updatedPizza.getSize());
        assertEquals(PizzaStatus.UNAVAILABLE, updatedPizza.getStatus());
        assertEquals(2, updatedPizza.getOrderId());
        assertEquals(2, updatedPizza.getCafeId());
        assertEquals(15.0, updatedPizza.getPrice());
    }

    @Test
    void testConvert_withNullUpdates_shouldNotUpdatePizzaObject() {
        // Arrange
        Pizza pizza = new Pizza();
        pizza.setPizzaName(PizzaName.MARGHERITA);
        pizza.setSize(PizzaSize.KIDS);
        pizza.setStatus(PizzaStatus.READY_TO_ORDER);
        pizza.setOrderId(1);
        pizza.setCafeId(1);
        pizza.setPrice(BigDecimal.valueOf(10.0));

        Pizza pizzaUpdate = new Pizza(); // All attributes are null

        PizzaUpdateService pizzaUpdateService = new PizzaUpdateService();

        // Act
        Pizza updatedPizza = pizzaUpdateService.convert(pizza, pizzaUpdate);

        // Assert
        assertEquals(PizzaName.MARGHERITA, updatedPizza.getPizzaName());
        assertEquals(PizzaSize.KIDS, updatedPizza.getSize());
        assertEquals(PizzaStatus.READY_TO_ORDER, updatedPizza.getStatus());
        assertEquals(1, updatedPizza.getOrderId());
        assertEquals(1, updatedPizza.getCafeId());
        assertEquals(10.0, updatedPizza.getPrice());
    }
}