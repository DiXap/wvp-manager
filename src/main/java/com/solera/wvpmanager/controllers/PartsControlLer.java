package com.solera.wvpmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solera.wvpmanager.models.PartsModel;
import com.solera.wvpmanager.services.PartsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/part")
public class PartsControlLer {
    private final PartsService partsService;
 

    public PartsControlLer(PartsService partsService) {
        this.partsService = partsService;
    }

    //Create a new part
    @PostMapping
    public ResponseEntity<PartsModel> createPart(@RequestBody PartsModel partsModel) {
        PartsModel createdPart = partsService.createPart(partsModel);
        return new ResponseEntity<>(createdPart, HttpStatus.CREATED);
    }

    //Read all parts
    @GetMapping("/all")
    public ResponseEntity<Iterable<PartsModel>> getAllParts(){
      Iterable<PartsModel> allParts = partsService.getAllParts();
      return new ResponseEntity<>(allParts, HttpStatus.OK);
    }

    //Get a part by ID
    @GetMapping("/{id}")
    public ResponseEntity<PartsModel> getPartByID(@PathVariable int id) {
        PartsModel part = partsService.getPartByID(id);
        return new ResponseEntity<>(part, HttpStatus.OK);
    }

    

    //Update a part by ID
    @PutMapping("/{id}")
    public ResponseEntity<PartsModel> updatePart(@PathVariable Integer id, @RequestBody PartsModel updatedPart) {
        PartsModel part = partsService.updatePart(id, updatedPart);
        return new ResponseEntity<>(part, HttpStatus.OK);
    }

    //Delete a part by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartByID(@PathVariable int id) {
    partsService.deletePartById(id);
    return ResponseEntity.noContent().build();
    }


}
