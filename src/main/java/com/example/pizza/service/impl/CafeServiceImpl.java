package com.example.pizza.service.impl;

import com.example.pizza.entity.Cafe;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.CafeRepository;
import com.example.pizza.service.CafeService;
import com.example.pizza.service.conventer.CafeUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;
    private final CafeUpdateService cafeUpdateService;

    @Override
    public void createNewCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    @Override
    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }

    @Override
    public Cafe findById(Integer id) {
        Optional<Cafe> cafeOptional = cafeRepository.findById(id);
        return cafeOptional.orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    @Transactional
    public Cafe update(Integer id, Cafe cafeUpdate) {
        Optional<Cafe> cafeOptional = cafeRepository.findById(id);
        if (cafeOptional.isPresent()) {
            Cafe existingCafe = cafeOptional.get();
            Cafe updated = cafeUpdateService.convert(existingCafe, cafeUpdate);
            cafeRepository.save(updated);
        }
        return cafeOptional.orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    public void delete(Integer id) {
        cafeRepository.deleteById(id);
    }

}
