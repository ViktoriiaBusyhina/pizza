package com.example.pizza.controller;

import com.example.pizza.entity.Cafe;
import com.example.pizza.service.CafeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CafeController {

    private final CafeService cafeService;

    @PostMapping(value = "/new-cafe")
    public ResponseEntity<Cafe> createNewCafe(@RequestBody Cafe cafe) {
        cafeService.createNewCafe(cafe);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/cafe/find/all")
    public ResponseEntity<List<Cafe>> findAllCafes() {
        List<Cafe> cafeList = cafeService.findAll();
        if (cafeList != null && !cafeList.isEmpty()) {
            return ResponseEntity.ok(cafeList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/cafe/find/{uuid}")
    public ResponseEntity<Cafe> findCafeByUuid(@PathVariable UUID uuid) {
        Cafe cafe = cafeService.findById(uuid);
        return ResponseEntity.ok(cafe);
    }

    @PutMapping(value = "/cafe/update/{uuid}")
    public ResponseEntity<Cafe> updateCafe(@PathVariable UUID uuid, @RequestBody Cafe cafe) {
        Cafe cafeUpdate = cafeService.update(uuid, cafe);
        return ResponseEntity.ok(cafeUpdate);
    }

    @DeleteMapping(value = "/cafe/delete/{uuid}")
    public ResponseEntity<String> deleteCafe(@PathVariable UUID uuid) {
        cafeService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
