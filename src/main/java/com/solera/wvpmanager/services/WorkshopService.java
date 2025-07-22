package com.solera.wvpmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solera.wvpmanager.models.WorkshopModel;
import com.solera.wvpmanager.repositories.WorkshopRepository;

@Service
public class WorkshopService {
    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }


    /* Create */
    public WorkshopModel createWorkshop(WorkshopModel workshop) throws IllegalArgumentException {
        if (workshop == null)
            throw new IllegalArgumentException("Workshop cannot be null");

        if (workshop.getName() == null || workshop.getName().isEmpty())
            throw new IllegalArgumentException("Invalid workshop name");

        if (workshop.getEmail() == null || workshop.getEmail().isEmpty())
            throw new IllegalArgumentException("Invalid workshop email");

        return workshopRepository.save(workshop);
    }


    /* Read */
    public WorkshopModel getWorkShopById(int workshopId) throws IllegalArgumentException {
        if (workshopId <= 0)
            throw new IllegalArgumentException("Invalid workshop Id: " + workshopId);

        return workshopRepository.findById(workshopId).orElse(null);
    }

    public List<WorkshopModel> getAllWorkshops() {
        return workshopRepository.findAll();
    }


    /* Update */
    public WorkshopModel updateWorkshop(WorkshopModel workshop) throws IllegalArgumentException {
        // TODO. Update specified fields insted of the whole object and setting null values
        if(workshop == null)
            throw new IllegalArgumentException("Workshop cannot be null");

        if(!workshopRepository.existsById(workshop.getId()))
            throw new IllegalArgumentException("Workshop does not exists");

        return workshopRepository.save(workshop);
    }


    /* Delete */
    public void deleteWorkshopById(int workshopId) throws IllegalArgumentException {
        if (workshopId <= 0)
            throw new IllegalArgumentException("Invalid workshop Id: " + workshopId);

        if (!workshopRepository.existsById(workshopId))
            throw new IllegalArgumentException("Workshop does not exists");

        workshopRepository.deleteById(workshopId);
    }


    /* Vehicle related ops */
}
