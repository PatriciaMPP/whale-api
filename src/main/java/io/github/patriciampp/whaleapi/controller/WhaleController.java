package io.github.patriciampp.whaleapi.controller;

import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.service.WhaleService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/whale-api")
public class WhaleController {

    @Autowired
    WhaleService whaleService;

    @GetMapping(path = "/whales")
    public ResponseEntity<ArrayList<Whale>> find(){

        ArrayList<Whale> whaleList = whaleService.getAll();

        if (whaleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(whaleList, HttpStatus.OK);
    }

    @GetMapping(path = "/whales/{id}")
    public ResponseEntity<Whale> findById(@PathVariable ("id") int id){
        try{
            return new ResponseEntity<>(whaleService.findById(id), HttpStatus.OK);
        } catch(NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/whales")
    public ResponseEntity<Void> deleteAll(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/whales/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable ("id") int id){
        return ResponseEntity.ok(whaleService.deleteById(id));
    }

    @PostMapping(path = "/whales")
    public ResponseEntity<Whale> create(@RequestBody JSONObject whaleJSON){
        String specieName = whaleJSON.get("specieName").toString();
        String latinName = whaleJSON.get("latinName").toString();
        String lifeSpan = whaleJSON.get("lifeSpan").toString();
        String description = whaleJSON.get("description").toString();
        String size = whaleJSON.get("size").toString();
        String weight = whaleJSON.get("weight").toString();

        Whale whale = new Whale (specieName, latinName, lifeSpan, description, size, weight);
        Whale whaleCreated = whaleService.add(whale);

        URI whaleUri = ServletUriComponentsBuilder.fromCurrentRequest().path(whaleCreated.toString()).build().toUri();
        return ResponseEntity.created(whaleUri).body(null);
    }

    @PutMapping(path = "/whales/{id}")
    public ResponseEntity<Whale> update(@PathVariable ("id") int id, JSONObject whaleJSON){
        System.out.println(id);
        Whale whaleToUpdate = whaleService.findById(id);
        Whale whaleUpdated = whaleService.update(whaleToUpdate, whaleJSON);
        return ResponseEntity.ok(whaleUpdated);
    }

    //Specific Methods

    @GetMapping(path = "/whales/min-size/{min-size}")
    public ResponseEntity<Iterable<Whale>> findMinSize(@PathVariable ("min-size") int minSize){
        return ResponseEntity.ok(whaleService.findWhalesByMinSize(minSize));
    }
}
