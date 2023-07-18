package com.example.pizza.dto.mapper;

import com.example.pizza.dto.PizzaDto;
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
class PizzaDtoMapperTest {

    private final PizzaDtoMapper pizzaDtoMapper = new PizzaDtoMapper();

    @Test
    void dtoToEntity_ShouldMapDtoToEntity_ok() {
        // Arrange
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setId(1);
        pizzaDto.setPizzaName("MARGHERITA");
        pizzaDto.setSize("NORMAL");
        pizzaDto.setQuantity(2);
        pizzaDto.setStatus("READY_TO_ORDER");
        pizzaDto.setOrderId(3);
        pizzaDto.setCafeId(4);
        pizzaDto.setPrice(BigDecimal.valueOf(12.99));

        // Act
        Pizza result = pizzaDtoMapper.dtoToEntity(pizzaDto);

        // Assert
        assertEquals(pizzaDto.getId(), result.getId());
        assertEquals(PizzaName.MARGHERITA, result.getPizzaName());
        assertEquals(PizzaSize.NORMAL, result.getSize());
        assertEquals(pizzaDto.getQuantity(), result.getQuantity());
        assertEquals(PizzaStatus.READY_TO_ORDER, result.getStatus());
        assertEquals(pizzaDto.getOrderId(), result.getOrderId());
        assertEquals(pizzaDto.getCafeId(), result.getCafeId());
        assertEquals(pizzaDto.getPrice(), result.getPrice());
    }

    @Test
    void entityToDto_ShouldMapEntityToDto_ok() {
        // Arrange
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setPizzaName(PizzaName.HAWAII);
        pizza.setSize(PizzaSize.KIDS);
        pizza.setQuantity(2);
        pizza.setStatus(PizzaStatus.READY_TO_ORDER);
        pizza.setOrderId(3);
        pizza.setCafeId(4);
        pizza.setPrice(BigDecimal.valueOf(12.00));

        // Act
        PizzaDto result = pizzaDtoMapper.entityToDto(pizza);

        // Assert
        assertEquals(pizza.getId(), result.getId());
        assertEquals(String.valueOf(pizza.getPizzaName()), result.getPizzaName());
        assertEquals(String.valueOf(pizza.getSize()), result.getSize());
        assertEquals(pizza.getQuantity(), result.getQuantity());
        assertEquals(String.valueOf(pizza.getStatus()), result.getStatus());
        assertEquals(pizza.getOrderId(), result.getOrderId());
        assertEquals(pizza.getCafeId(), result.getCafeId());
        assertEquals(pizza.getPrice(), result.getPrice());
    }
}