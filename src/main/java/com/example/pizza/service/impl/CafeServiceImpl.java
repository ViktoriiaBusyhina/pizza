package com.example.pizza.service.impl;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.dto.mapper.CafeDtoMapper;
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
    private final CafeDtoMapper cafeDtoMapper;

    @Override
    public void createNewCafe(CafeDto cafeDto) {
        Cafe cafe = cafeDtoMapper.dtoToEntity(cafeDto);
        cafeRepository.save(cafe);
    }

    @Override
    public List<CafeDto> findAll() {
        List<Cafe> cafes = cafeRepository.findAll();
        return cafes.stream()
                .map(cafeDtoMapper::entityToDto)
                .toList();
    }

    @Override
    public CafeDto findById(Integer id) {
        Optional<Cafe> cafeOptional = cafeRepository.findById(id);
        Cafe cafe = cafeOptional.orElseThrow(DataNotFoundException::new);
        return cafeDtoMapper.entityToDto(cafe);
    }

    @Override
    @Transactional
    public void update(Integer id, CafeDto cafe) {
        Cafe existingCafe = cafeRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        Cafe cafeUpdate = cafeDtoMapper.dtoToEntity(cafe);
        Cafe updated = cafeUpdateService.convert(existingCafe, cafeUpdate);
        cafeRepository.save(updated);
    }

    @Override
    public void delete(Integer id) {
        cafeRepository.deleteById(id);
    }

}
