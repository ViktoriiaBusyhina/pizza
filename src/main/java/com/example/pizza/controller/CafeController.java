package com.example.pizza.controller;

import com.example.pizza.dto.CafeDto;
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
    public ResponseEntity<CafeDto> createNewCafe(@RequestBody CafeDto cafe) {
        cafeService.createNewCafe(cafe);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of all cafes.
     *
     * @return a ResponseEntity containing the list of cafes or indicating no content
     */
    @GetMapping(value = "/cafe/find/all")
    public ResponseEntity<List<CafeDto>> findAllCafes() {
        List<CafeDto> cafeList = cafeService.findAll();
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
    public ResponseEntity<CafeDto> findCafeById(@PathVariable Integer id) {
        CafeDto cafe = cafeService.findById(id);
        return ResponseEntity.ok(cafe);
    }

    /**
     * Updates a cafe with the specified ID.
     *
     * @param id   the ID of the cafe to update
     * @param cafe the updated cafe object
     * @return a ResponseEntity with the updated cafe or HTTP status 404 if the cafe is not found
     */
    @PutMapping(value = "/cafe/update/{id}")
    public ResponseEntity<CafeDto> updateCafe(@PathVariable Integer id, @RequestBody CafeDto cafe) {
        cafeService.update(id, cafe);
        return ResponseEntity.ok(cafe);
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
