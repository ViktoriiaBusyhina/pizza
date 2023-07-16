package com.example.pizza.dto.mapper;
/**
 * The DtoMapper interface represents a generic mapper interface that defines methods to map between entity objects and DTO objects.
 *
 * @param <E> the type of the entity object
 * @param <D> the type of the DTO object
 */
public interface DtoMapper<E, D> {

    /**
     * Converts a DTO object to an entity object.
     *
     * @param dto the DTO object to convert
     * @return the converted entity object
     */
    E dtoToEntity(D dto);

    /**
     * Converts an entity object to a DTO object.
     *
     * @param entity the entity object to convert
     * @return the converted DTO object
     */
    D entityToDto(E entity);
}