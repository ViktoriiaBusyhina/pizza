package com.example.pizza.service.impl;

import com.example.pizza.dto.OrderDto;
import com.example.pizza.dto.PizzaDto;
import com.example.pizza.dto.mapper.PizzaDtoMapper;
import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PizzaStatus;
import com.example.pizza.entity.Pizza;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.service.OrderService;
import com.example.pizza.service.PizzaService;
import com.example.pizza.service.conventer.PizzaUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaUpdateService pizzaUpdateService;
    private final PizzaDtoMapper pizzaDtoMapper;
    private final OrderService orderService;

    @Override
    public void createNewPizza(PizzaDto pizzaDto) {
        Pizza pizza = pizzaDtoMapper.dtoToEntity(pizzaDto);
        pizzaRepository.save(pizza);
    }

    @Override
    public List<PizzaDto> findAll() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream()
                .map(pizzaDtoMapper::entityToDto)
                .toList();
    }

    @Override
    public PizzaDto findById(Integer id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        return pizzaDtoMapper.entityToDto(pizza);
    }

    @Override
    @Transactional
    public void update(Integer id, PizzaDto pizza) {
        Pizza existingPizza = pizzaRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        Pizza pizzaUpdate = pizzaDtoMapper.dtoToEntity(pizza);
        Pizza updated = pizzaUpdateService.convert(existingPizza, pizzaUpdate);
        pizzaRepository.save(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pizzaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void blockPizzaById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()) {
            Pizza pizza = pizzaOptional.get();
            pizza.setStatus(PizzaStatus.valueOf(String.valueOf(PizzaStatus.UNAVAILABLE)));
            pizzaRepository.save(pizza);
        }
    }
    @Override
    @Transactional
    public void orderNewPizza(Integer customerId, PizzaDto pizzaDto) { //делаем заказ пиццы по айди клиента
        OrderDto order = new OrderDto();
        Pizza pizza = pizzaDtoMapper.dtoToEntity(pizzaDto);
        order.setCustomerId(customerId); //присваеваем айди клиента
        order.setOrderStatus(OrderStatus.PROCESSING.toString()); //устанавливаем статус "в процессе"
        orderService.createNewOrder(order); //сохраняем его в баззу данных

        pizza.setOrderId(order.getId()); //добавляем для пиццы номер заказа
        pizzaRepository.save(pizza);
    }
}
