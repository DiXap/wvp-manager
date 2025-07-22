package com.solera.wvpmanager.services;
import java.util.List;
import com.solera.wvpmanager.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;
import com.solera.wvpmanager.models.VehicleModel;
import com.solera.wvpmanager.models.Vp;
import com.solera.wvpmanager.repositories.VehicleRepository;


@Service
public class VehicleService {

    private final WorkshopRepository workshopRepository;
    private final VehicleRepository vehicleRepository;
    public VehicleService(VehicleRepository vehicleRepository, WorkshopRepository workshopRepository) {
        this.vehicleRepository = vehicleRepository;
        this.workshopRepository = workshopRepository;
    }

    /* Create */
    public VehicleModel createNewVehicle(VehicleModel newVehicle) throws IllegalArgumentException {
        if(newVehicle == null){
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if(newVehicle.getVin() == null || newVehicle.getVin().isEmpty() || newVehicle.getVin().length()!= 17){
            throw new IllegalArgumentException("Invalid vehicle model or VIN: " + newVehicle);
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

    /* Read all vehicles */
    public Iterable<VehicleModel> getAllVehicles() {
        if(vehicleRepository.count() == 0){
            throw new IllegalArgumentException("No vehicles found");
        }
        return vehicleRepository.findAll();
    }

    //Asign a workshop to a vehicle
    public VehicleModel assignWorkshopToVehicle(Integer vehicleId, Integer workshopId) throws IllegalArgumentException {
         if (vehicleId == null || vehicleId <= 0 || !vehicleRepository.existsById(vehicleId)) {
        throw new IllegalArgumentException("Invalid vehicle ID: " + vehicleId);
        }
        if (workshopId == null || workshopId <= 0 || !workshopRepository.existsById(workshopId)) {
        throw new IllegalArgumentException("Invalid workshop ID: " + workshopId);
        }
        VehicleModel vehicle = vehicleRepository.findById(vehicleId).get();
        vehicle.setWorkshop(workshopRepository.findById(workshopId).get());
        return vehicleRepository.save(vehicle);
    }

    //Get parts in a vehicle
    public List<Vp> getPartsInVehicle(Integer id) throws IllegalArgumentException {
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + id));
        return vehicle.getParts();
    }

    /* Update */
    public VehicleModel updateVehicle(VehicleModel upVehicle) throws IllegalArgumentException {
        if(upVehicle == null){
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if(!vehicleRepository.existsById(upVehicle.getId())){
            throw new IllegalArgumentException("Vehicle does not exist with ID: " + upVehicle.getId());
        }
        if(!upVehicle.getVin().isEmpty()){
            throw new IllegalArgumentException("Vehicle VIN cannot be changed");
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
