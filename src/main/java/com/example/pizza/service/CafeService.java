package com.example.pizza.service;

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
    void createNewCafe(Cafe cafe);

    /**
     * Returns a list of all cafes.
     *
     * @return a list of all cafes
     */
    List<Cafe> findAll();

    /**
     * Finds a cafe by its ID.
     *
     * @param id the ID of the cafe to find
     * @return the cafe with the specified ID, or null if not found
     */
    Cafe findById(Integer id);

    /**
     * Updates a cafe with the specified ID.
     *
     * @param id    the ID of the cafe to update
     * @param cafe  the updated cafe object
     * @return the updated cafe
     */
    Cafe update(Integer id, Cafe cafe);

    /**
     * Deletes a cafe with the specified ID.
     *
     * @param id the ID of the cafe to delete
     */
    void delete(Integer id);
}
