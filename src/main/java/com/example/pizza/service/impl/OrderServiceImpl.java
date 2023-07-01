package com.example.pizza.service.impl;

import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Order;
import com.example.pizza.exception.OrderNotAllowedException;
import com.example.pizza.repository.OrderRepository;
import com.example.pizza.service.CustomerService;
import com.example.pizza.service.OrderService;
import com.example.pizza.service.conventer.OrderUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderUpdateService orderUpdateService;
    private final CustomerService customerService;

    @Override
    @Transactional  //проверка на заполнения полей от клиента, и проверка способа оплаты
    public void createNewOrder(Order order) {
        Integer customerId = order.getCustomerId();
        Customer customer = customerService.findById(customerId);
        if (isValidCustomer(customer)) {
            throw new IllegalArgumentException();
        }
        if(customer.isBlocked()){
            throw new OrderNotAllowedException( "мы вас больше не обслуживаем");// проверка на блокировку клиента
        }
        order.setPaymentMethod(customer.getPaymentMethod());//берем способ оплаты у клиента для заказа

        orderRepository.save(order);
    }

    private boolean isValidCustomer(Customer customer) {
        return customer.getName() != null && customer.getAddress() != null
                && customer.getPhone() != null && customer.getEmail() != null
                && customer.getPaymentMethod() != null;
    }

    @Override
    public Order findById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order update(Integer id, Order orderUpdate) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            Order updated = orderUpdateService.convert(existingOrder, orderUpdate);
            orderRepository.save(updated);
        }
        return orderOptional.orElse(null);


    }

    @Override
    @Transactional
    public void delete(Integer id) {
        orderRepository.deleteById(id);

    }

    @Override
    @Transactional
    public String checkOrderStatus(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return order.getStatusOrder().name();
        }
        return "no status";


    }
}

