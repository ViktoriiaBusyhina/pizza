package com.example.pizza.repository;

import com.example.pizza.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CafeRepository extends JpaRepository<Cafe, UUID> {
}
