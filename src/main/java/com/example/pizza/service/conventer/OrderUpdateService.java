package com.example.pizza.service.conventer;

import com.example.pizza.entity.Order;
import com.example.pizza.entity.Pizza;
import org.springframework.stereotype.Service;

@Service
public class OrderUpdateService {

    public Order convert(Order order,  Order orderUpdate) {
        if ( orderUpdate.getCustomerId() != null) {
            order.setCustomerId(orderUpdate.getCustomerId());
        }
        if (orderUpdate.getPaymentMethod() != null) {
            order.setPaymentMethod(orderUpdate.getPaymentMethod());
        }
        if (orderUpdate.getStatusOrder() != null) {
            order.setStatusOrder(orderUpdate.getStatusOrder());
        }

        return order;

    }
}
