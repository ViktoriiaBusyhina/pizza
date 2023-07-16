package com.example.pizza.controller;

import com.example.pizza.entity.Cafe;
import com.example.pizza.service.CafeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The CafeController class handles cafe-related API endpoints.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class CafeController {



    private final CafeService cafeService;

    /**
     * Creates a new cafe.
     *
     * @param cafe the cafe to create
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @PostMapping(value = "/new-cafe")
    public ResponseEntity<Cafe> createNewCafe(@RequestBody Cafe cafe) {
        cafeService.createNewCafe(cafe);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of all cafes.
     *
     * @return a ResponseEntity containing the list of cafes or indicating no content
     */
    @GetMapping(value = "/cafe/find/all")
    public ResponseEntity<List<Cafe>> findAllCafes() {
        List<Cafe> cafeList = cafeService.findAll();
        if (cafeList != null && !cafeList.isEmpty()) {
            return ResponseEntity.ok(cafeList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Retrieves a cafe by its ID.
     *
     * @param id the ID of the cafe to retrieve
     * @return a ResponseEntity containing the retrieved cafe
     */
    @GetMapping(value = "/cafe/find/{id}")
    public ResponseEntity<Cafe> findCafeByUuid(@PathVariable Integer id) {
        Cafe cafe = cafeService.findById(id);
        return ResponseEntity.ok(cafe);
    }

    /**
     * Updates a cafe with the specified ID.
     *
     * @param id   the ID of the cafe to update
     * @param cafe the updated cafe object
     * @return a ResponseEntity containing the updated cafe
     */
    @PutMapping(value = "/cafe/update/{id}")
    public ResponseEntity<Cafe> updateCafe(@PathVariable Integer id, @RequestBody Cafe cafe) {
        Cafe cafeUpdate = cafeService.update(id, cafe);
        return ResponseEntity.ok(cafeUpdate);
    }

    /**
     * Deletes a cafe with the specified ID.
     *
     * @param id the ID of the cafe to delete
     * @return a ResponseEntity with HTTP status indicating the success of the operation
     */
    @DeleteMapping(value = "/cafe/delete/{id}")
    public ResponseEntity<String> deleteCafe(@PathVariable Integer id) {
        cafeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
