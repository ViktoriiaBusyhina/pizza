package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.entity.Cafe;
import org.springframework.stereotype.Component;
@Component
public class CafeDtoMapper implements DtoMapper<Cafe, CafeDto> {

    public Cafe dtoToEntity(CafeDto cafeDto) {
        Cafe cafe = new Cafe();
        cafe.setId(cafeDto.getId());
        cafe.setPizzaId(cafeDto.getPizzaId());
        cafe.setName(cafeDto.getName());
        cafe.setAddress(cafeDto.getAddress());
        cafe.setPhone(cafeDto.getPhone());
        cafe.setSchedule(cafeDto.getSchedule());
        return cafe;
    }

    public CafeDto entityToDto(Cafe cafe) {
        CafeDto cafeDto = new CafeDto();
        cafeDto.setId(cafe.getId());
        cafeDto.setPizzaId(cafe.getPizzaId());
        cafeDto.setName(cafe.getName());
        cafeDto.setAddress(cafe.getAddress());
        cafeDto.setPhone(cafe.getPhone());
        cafeDto.setSchedule(cafe.getSchedule());
        return cafeDto;
    }
}
