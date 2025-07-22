package com.solera.wvpmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solera.wvpmanager.models.VehicleModel;
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
    public WorkshopModel updateWorkshop(int workshopId, WorkshopModel workshop) throws IllegalArgumentException {
        // TODO. Update specified fields insted of the whole object and setting null values
        if(workshop == null)
            throw new IllegalArgumentException("Workshop cannot be null");

        WorkshopModel currentWorkshop = this.getWorkShopById(workshopId); // Returns null if not found. Could throw if illegal id

        if(currentWorkshop == null)
            throw new IllegalArgumentException("Workshop does not exists");

        if (workshop.getName() != null)
            currentWorkshop.setName(workshop.getName());
        
        if (workshop.getEmail() != null)
            currentWorkshop.setEmail(workshop.getEmail());

        return workshopRepository.save(currentWorkshop);
    }


    /* Delete */
    public void deleteWorkshopById(int workshopId) throws IllegalArgumentException {
        if (workshopId <= 0)
            throw new IllegalArgumentException("Invalid workshop Id: " + workshopId);

        if (!workshopRepository.existsById(workshopId))
            throw new IllegalArgumentException("Workshop does not exists");

        if(!this.getWorkShopById(workshopId).getVehicles().isEmpty())
            throw new IllegalArgumentException("Cannot delete a workshop with vehicles assigned");

        workshopRepository.deleteById(workshopId);
    }


    /* Vehicle related ops */
    public List<VehicleModel> getWorkshopVehicles(int workshopId) throws IllegalArgumentException {
        if (workshopId <= 0)
            throw new IllegalArgumentException("Invalid workshop Id: " + workshopId);

        return workshopRepository.findById(workshopId).map(WorkshopModel::getVehicles).orElse(null);
    }
}
