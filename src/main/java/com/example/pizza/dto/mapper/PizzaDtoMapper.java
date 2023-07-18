package com.example.pizza.dto.mapper;

import com.example.pizza.dto.PizzaDto;
import com.example.pizza.enam.PizzaName;
import com.example.pizza.enam.PizzaSize;
import com.example.pizza.enam.PizzaStatus;
import com.example.pizza.entity.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaDtoMapper implements DtoMapper<Pizza, PizzaDto> {

    public Pizza dtoToEntity(PizzaDto pizzaDto) {
        Pizza pizza = new Pizza();
        pizza.setId(pizzaDto.getId());
        pizza.setPizzaName(PizzaName.valueOf(pizzaDto.getPizzaName()));
        pizza.setSize(PizzaSize.valueOf(pizzaDto.getSize()));
        pizza.setQuantity(pizzaDto.getQuantity());
        pizza.setStatus(PizzaStatus.valueOf(pizzaDto.getStatus()));
        pizza.setOrderId(pizzaDto.getOrderId());
        pizza.setCafeId(pizzaDto.getCafeId());
        pizza.setPrice(pizzaDto.getPrice());
        return pizza;
    }

    public PizzaDto entityToDto(Pizza pizza) {
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setId(pizza.getId());
        pizzaDto.setPizzaName(String.valueOf(pizza.getPizzaName()));
        pizzaDto.setSize(String.valueOf(pizza.getSize()));
        pizzaDto.setQuantity(pizza.getQuantity());
        pizzaDto.setStatus(String.valueOf(pizza.getStatus()));
        pizzaDto.setOrderId(pizza.getOrderId());
        pizzaDto.setCafeId(pizza.getCafeId());
        pizzaDto.setPrice(pizza.getPrice());
        return pizzaDto;
    }
}
