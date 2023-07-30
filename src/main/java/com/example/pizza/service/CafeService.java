package com.example.pizza.service;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.entity.Cafe;

import java.util.List;

/**
 * The CafeService interface provides methods for managing cafes.
 */
public interface CafeService {

    /**
     * Creates a new cafe.
     *
     * @param cafe the cafe to create
     */
    void createNewCafe(CafeDto cafe);

    /**
     * Returns a list of all cafes.
     *
     * @return a list of all cafes
     */
    List<CafeDto> findAll();

    /**
     * Finds a cafe by its ID.
     *
     * @param id the ID of the cafe to find
     * @return the cafe with the specified ID, or null if not found
     */
    CafeDto findById(Integer id);

    /**
     * Updates a cafe with the specified ID.
     *
     * @param id    the ID of the cafe to update
     * @param cafe  the updated cafe object
     */
    void update(Integer id, CafeDto cafe);

    /**
     * Deletes a cafe with the specified ID.
     *
     * @param id the ID of the cafe to delete
     */
    void delete(Integer id);
}
