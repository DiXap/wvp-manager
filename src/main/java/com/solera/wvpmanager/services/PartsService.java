package com.solera.wvpmanager.services;
import com.solera.wvpmanager.models.PartsModel;
import com.solera.wvpmanager.repositories.PartsRepository;

public class PartsService{
    private final PartsRepository partsRepository;

    //Costructor to inject the Jpa repository of Parts allows us to use the methods provided by Spring Data JPA
    public PartsService(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    //Create a new part
    public void createPart(PartsModel part) throws IllegalArgumentException{

        if(part.getParts_name()==null || part.getBrand_part()==null || part.getPart_num() == null){
            throw new IllegalArgumentException("Part name cannot be empty");
        }
        partsRepository.save(part);
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
    public void updatePart(int id, PartsModel updatedPart){
        PartsModel prevPart = getPartByID(id);
        if (prevPart != null) {
            prevPart.setParts_name(updatedPart.getParts_name());
            prevPart.setPart_num(updatedPart.getPart_num());
            prevPart.setBrand_part(updatedPart.getBrand_part());
            partsRepository.save(prevPart);
        }  
    }

    //Delete a part
    public void deletePart(int id){
        if(getPartByID(id) == null){
            throw new IllegalArgumentException("Part with ID " + id + " not found.");
        }
            partsRepository.deleteById(id);
    }

    
}