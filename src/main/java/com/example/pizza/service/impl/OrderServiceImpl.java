package com.example.pizza.service.impl;

import com.example.pizza.enam.OrderStatus;
import com.example.pizza.enam.PaymentStatus;
import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Order;
import com.example.pizza.exception.DataNotFoundException;
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
    private static final String EXCEPTION_MESSAGE = "Order is not allowed";
    private final OrderRepository orderRepository;
    private final OrderUpdateService orderUpdateService;
    private final CustomerService customerService;
    private static final String noStatus = "no status";

    @Override
    @Transactional  //проверка на заполнения полей от клиента, и проверка способа оплаты
    public void createNewOrder(Order order) {
        Integer customerId = order.getCustomerId();
        Customer customer = customerService.findById(customerId);
        if (!isValidCustomer(customer)) {
            throw new IllegalArgumentException();
        }
        if (isOrderAllowed(order, customer)) { //поверкка на способность оплаты
            throw new OrderNotAllowedException(EXCEPTION_MESSAGE);// проверка на блокировку клиента
        }
        if (order.getPaymentStatus() == PaymentStatus.SUCCESSFUL) {
            order.setPaymentMethod(customer.getPaymentMethod());//берем способ оплаты у клиента для заказа
            order.setOrderStatus(OrderStatus.PROCESSING);

            orderRepository.save(order);
        }
    }

    private boolean isOrderAllowed(Order order, Customer customer) {
        return customer.isBlocked() || customer.getPaymentMethod() == null
                || order.getPaymentStatus() == PaymentStatus.FAILED;
    }

    private boolean isValidCustomer(Customer customer) {
        return customer.getName() != null && customer.getAddress() != null
                && customer.getPhone() != null && customer.getEmail() != null
                && customer.getPaymentMethod() != null;
    }

    @Override
    public Order findById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.orElseThrow(() -> new DataNotFoundException());
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
            return updated;
        }
        throw new DataNotFoundException();


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
            return order.getOrderStatus().name();
        }
        return noStatus;
    }
}

