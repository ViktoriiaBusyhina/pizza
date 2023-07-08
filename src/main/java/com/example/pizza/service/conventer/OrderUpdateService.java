package com.example.pizza.service.conventer;

import com.example.pizza.entity.Order;
import org.springframework.stereotype.Service;

/**
 * The OrderUpdateService class provides a method to update an order based on the changes specified in another order object.
 */
@Service
public class OrderUpdateService {

    /**
     * Updates the provided order based on the changes specified in the orderUpdate object.
     *
     * @param order         the original order object
     * @param orderUpdate   the order object containing the updates
     * @return the updated order
     */
    public Order convert(Order order, Order orderUpdate) {
        if (orderUpdate.getCustomerId() != null) {
            order.setCustomerId(orderUpdate.getCustomerId());
        }
        if (orderUpdate.getPaymentMethod() != null) {
            order.setPaymentMethod(orderUpdate.getPaymentMethod());
        }
        if (orderUpdate.getOrderStatus() != null) {
            order.setOrderStatus(orderUpdate.getOrderStatus());
        }

        return order;
    }
}

