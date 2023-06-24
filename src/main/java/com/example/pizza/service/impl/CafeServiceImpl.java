package com.example.pizza.service.impl;

import com.example.pizza.entity.Cafe;
import com.example.pizza.entity.Customer;
import com.example.pizza.repository.CafeRepository;
import com.example.pizza.repository.CustomerRepository;
import com.example.pizza.service.CafeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;

    @Override
    public void createNewCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    @Override
    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }

    @Override
    public Cafe findById(UUID uuid) {
        Optional<Cafe> cafeOptional = cafeRepository.findById(uuid);
        return cafeOptional.orElse(null);
    }

    @Override
    @Transactional
    public Cafe update(UUID uuid, Cafe cafeUpdate) {
        Optional<Cafe> cafeOptional = cafeRepository.findById(uuid);
        if (cafeOptional.isPresent()) {
            Cafe existingCafe = cafeOptional.get();
            Cafe updated = cafeUpdateServise.transform(existingCafe, cafeUpdate);
            cafeRepository.save(updated);
        }
        return cafeOptional.orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
        cafeRepository.deleteById(uuid);
    }

}
