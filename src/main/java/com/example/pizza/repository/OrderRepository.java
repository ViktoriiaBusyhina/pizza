package com.example.pizza.repository;

import com.example.pizza.entity.Customer;
import com.example.pizza.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
