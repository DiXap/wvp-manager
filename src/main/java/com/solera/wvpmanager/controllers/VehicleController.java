package com.solera.wvpmanager.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.solera.wvpmanager.models.VehicleModel;
import com.solera.wvpmanager.models.Vp;
import com.solera.wvpmanager.services.VehicleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //Create a new vehicle
    @PostMapping
    public ResponseEntity<VehicleModel> createNewVehicle(@RequestBody VehicleModel newVehicle) {
        VehicleModel createdVehicle = vehicleService.createNewVehicle(newVehicle);
        return ResponseEntity.ok().body(createdVehicle);
    }

    //Read all vehicles
    @GetMapping("/all")
    public ResponseEntity<Iterable<VehicleModel>> getAllVehicles() {
        Iterable<VehicleModel> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    //Get a vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleModel> getVehicleById(@PathVariable Integer id) {
        VehicleModel vehicle = vehicleService.getVehicleById(id);
        return vehicle != null ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
    }

    //get parts in a vehicle
    @GetMapping("/parts/{id}")
    public ResponseEntity<List<Vp>> getPartsInVehicle(@PathVariable Integer id) {
        List<Vp> parts = vehicleService.getPartsInVehicle(id);
        return ResponseEntity.ok(parts);
    }

    //Update a vehicle
    @PutMapping
    public ResponseEntity<VehicleModel> updateVehicle(@RequestBody VehicleModel upVehicle) {
        VehicleModel updatedVehicle = vehicleService.updateVehicle(upVehicle);
        return updatedVehicle != null ? ResponseEntity.ok(updatedVehicle) : ResponseEntity.notFound().build();
    }

    //Assign a workshop to a vehicle
    @PutMapping("/{vehicleId}/toworkshop/{workshopId}")
    public ResponseEntity<VehicleModel> assignVehicleToWorkshop(@PathVariable Integer vehicleId, @PathVariable Integer workshopId) {
        VehicleModel updatedVehicle = vehicleService.assignWorkshopToVehicle(vehicleId, workshopId);
        return ResponseEntity.ok(updatedVehicle);
    }

    //Delete a vehicle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Integer id) {

        vehicleService.deleteVehicleById(id);
        return ResponseEntity.ok("Vehicle deleted successfully" + " with ID: " + id);
    }




}
