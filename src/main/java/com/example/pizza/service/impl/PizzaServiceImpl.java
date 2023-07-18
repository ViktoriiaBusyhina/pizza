package com.example.pizza.service.impl;

import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PizzaStatus;
import com.example.pizza.entity.Order;
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
    private final OrderService orderService;

    @Override
    public void createNewPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        return pizzaOptional.orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    @Transactional
    public Pizza update(Integer id, Pizza pizzaUpdate) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()) {
            Pizza existingPizza = pizzaOptional.get();
            Pizza updated = pizzaUpdateService.convert(existingPizza, pizzaUpdate);
            pizzaRepository.save(updated);
            return updated;
        }
        throw new DataNotFoundException();
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
    public void orderNewPizza(Integer customerId, Pizza pizza) { //делаем заказ пиццы по айди клиента
        Order order = new Order();
        order.setCustomerId(customerId); //присваеваем айди клиента
        order.setOrderStatus(OrderStatus.PROCESSING); //устанавливаем статус "в процессе"
//        order.setCafeId();
        orderService.createNewOrder(order); //сохраняем его в баззу данных

        pizza.setOrderId(order.getId()); //добавляем для пиццы номер заказа
//        pizza.setCafeId();
        pizzaRepository.save(pizza);
    }
}
