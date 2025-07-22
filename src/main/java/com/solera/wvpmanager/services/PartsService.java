package com.solera.wvpmanager.services;
import org.springframework.stereotype.Service;

import com.solera.wvpmanager.models.PartsModel;
import com.solera.wvpmanager.repositories.PartsRepository;


@Service
public class PartsService{
    private final PartsRepository partsRepository;

    //Costructor to inject the Jpa repository of Parts allows us to use the methods provided by Spring Data JPA
    public PartsService(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    //Create a new part
    public PartsModel createPart(PartsModel part) throws IllegalArgumentException{

        if(part.getParts_name()==null || part.getBrand_part()==null || part.getPart_num() == null){
            throw new IllegalArgumentException("Part name cannot be empty");
        }
        return partsRepository.save(part);
    }

    //Read all parts
    public Iterable<PartsModel> getAllParts() throws IllegalArgumentException {
        if(partsRepository.count() == 0) {
            throw new IllegalArgumentException("No parts found in the database.");
        }
        return partsRepository.findAll();
    }

    //Read one part by ID
    public PartsModel getPartByID(int id) throws IllegalArgumentException {
        if(!partsRepository.existsById(id)){
            throw new IllegalArgumentException("Part with ID " + id + " not found.");
        }
        return partsRepository.findById(id).orElse(null);
    }

    //Update a part
    public PartsModel updatePart(int id, PartsModel updatedPart){
        PartsModel prevPart = getPartByID(id);
        if (prevPart != null) {
            prevPart.setParts_name(updatedPart.getParts_name());
            prevPart.setPart_num(updatedPart.getPart_num());
            prevPart.setBrand_part(updatedPart.getBrand_part());
            partsRepository.save(prevPart);
        }  
        return prevPart;
    }

    //Delete a part
    public void deletePartById(int PartId) throws IllegalArgumentException {
       if(PartId <= 0) {
            throw new IllegalArgumentException("Invalid part ID: " + PartId);
        }
        if(!partsRepository.existsById(PartId)) {
            throw new IllegalArgumentException("Part with ID " + PartId + " does not exist.");
        }
        partsRepository.deleteById(PartId);

    }

    
}