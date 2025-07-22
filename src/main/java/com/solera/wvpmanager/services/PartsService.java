package com.solera.wvpmanager.services;
import com.solera.wvpmanager.models.PartsModel;
import com.solera.wvpmanager.repositories.PartsRepository;

public class PartsService{
    private final PartsRepository partsRepository;

    public PartsService(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    //Create a new part
    public void createPart(PartsModel part){
        partsRepository.save(part);
    }

    //Read all parts
    public Iterable<PartsModel> getAllParts(){
        return partsRepository.findAll();
    }

    //Read one part by ID
    public PartsModel getPartByID(int id){
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
        if(getPartByID(id) != null){
            partsRepository.deleteById(id);
        }
    }
}