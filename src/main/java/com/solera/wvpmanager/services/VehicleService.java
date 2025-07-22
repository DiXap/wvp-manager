package com.solera.wvpmanager.services;

import org.springframework.stereotype.Service;

import com.solera.wvpmanager.models.VehicleModel;
import com.solera.wvpmanager.repositories.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /* Create */
    public VehicleModel createNewVehicle(VehicleModel newVehicle) throws IllegalArgumentException {
        if(newVehicle == null){
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if(newVehicle.getVin() == null || newVehicle.getVin().isEmpty()){
            throw new IllegalArgumentException("Vehicle VIN cannot be null or empty");
        }
        if(newVehicle.getModel() == null || newVehicle.getModel().isEmpty()){
            throw new IllegalArgumentException("Vehicle model cannot be null or empty");
        }
        return vehicleRepository.save(newVehicle);
    }

    /* Get a vehicle by ID */
    public VehicleModel getVehicleById(Integer id) throws IllegalArgumentException {
        if(id == null || id <= 0){
            throw new IllegalArgumentException("Invalid vehicle ID: " + id);
        }
        return vehicleRepository.findById(id).orElse(null);
    }

    /* Update */
    public VehicleModel updateVehicle(VehicleModel upVehicle) throws IllegalArgumentException {
        if(upVehicle == null){
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if(!vehicleRepository.existsById(upVehicle.getId())){
            throw new IllegalArgumentException("Vehicle does not exist with ID: " + upVehicle.getId());
        }
        return vehicleRepository.save(upVehicle);

    }

    /* Delete */
    public void deleteVehicleById(Integer id){
        if (id<=0) {
            throw new IllegalArgumentException("Invalid vehicle ID: " + id);
        }

        if(!vehicleRepository.existsById(id)){
            throw new IllegalArgumentException("Vehicle does not exist with ID: " + id);
        }
        vehicleRepository.deleteById(id);

    }
}
