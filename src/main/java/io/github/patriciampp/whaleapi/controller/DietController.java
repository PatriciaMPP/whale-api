package io.github.patriciampp.whaleapi.controller;

import io.github.patriciampp.whaleapi.persistence.model.Diet;
import io.github.patriciampp.whaleapi.persistence.repository.DietRepository;
import io.github.patriciampp.whaleapi.service.DietService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/whale-api")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private DietRepository dietRepository;

    @GetMapping(path = "/diets")
    public ResponseEntity<Iterable<Diet>> findAll() {

        return ResponseEntity.ok(dietService.getAll());
    }

    @GetMapping(path = "/diets/{id}")
    public ResponseEntity<Diet> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(dietService.findById(id));
    }

    @DeleteMapping(path = "/diets")
    public ResponseEntity<Void> deleteAll() {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/diets/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(dietService.deleteById(id));
    }

    @PostMapping(path = "/diets")
    public ResponseEntity<Diet> create(@RequestBody JSONObject dietJSON) {
        String dietText = dietJSON.get("diet").toString();
        Diet diet = new Diet(dietText);
        return ResponseEntity.ok(dietService.add(diet));
    }

    @PutMapping(path = "/diets/{id}")
    public ResponseEntity<Diet> update(@PathVariable("id") int id, JSONObject dietJSON) {
        Diet dietToUpdate = dietService.findById(id);
        return ResponseEntity.ok(dietService.update(dietToUpdate, dietJSON));
    }

}