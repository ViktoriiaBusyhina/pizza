package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.entity.Cafe;
import org.springframework.stereotype.Component;
/**
 * The CafeDtoMapper class is a component that maps between Cafe and CafeDto objects.
 */
@Component
public class CafeDtoMapper implements DtoMapper<Cafe, CafeDto> {

    /**
     * Converts a CafeDto object to a Cafe entity.
     *
     * @param cafeDto the CafeDto object to convert
     * @return the converted Cafe entity
     */
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

    /**
     * Converts a Cafe entity to a CafeDto object.
     *
     * @param cafe the Cafe entity to convert
     * @return the converted CafeDto object
     */
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