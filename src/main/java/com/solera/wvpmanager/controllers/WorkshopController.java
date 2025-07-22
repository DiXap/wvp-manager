package com.solera.wvpmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solera.wvpmanager.models.VehicleModel;
import com.solera.wvpmanager.models.WorkshopModel;
import com.solera.wvpmanager.services.WorkshopService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/workshop")
public class WorkshopController {
    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping
    public ResponseEntity<WorkshopModel> addWorkshop(@RequestBody WorkshopModel workshop) {
        WorkshopModel newWorkshop = workshopService.createWorkshop(workshop);

        return ResponseEntity.ok(newWorkshop); // TODO. Add descriptive msg
    }

    /* Read */
    @GetMapping("/{workshopId}")
    public ResponseEntity<WorkshopModel> getWorkshopById(@PathVariable int workshopId) {
        WorkshopModel workshop = workshopService.getWorkShopById(workshopId);

        return workshop != null ? ResponseEntity.ok(workshop) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<WorkshopModel>> getAllWorkshops() {
        List<WorkshopModel> workshops = workshopService.getAllWorkshops();

        return ResponseEntity.ok(workshops);
    }

    /* Update */
    @PutMapping("/{workshopId}")
    public ResponseEntity<WorkshopModel> updateWorkshop(@PathVariable int workshopId, @RequestBody WorkshopModel workshop) {
        // Consider DTO instead of Model
        WorkshopModel updatedWorkshop = workshopService.updateWorkshop(workshopId, workshop);

        return ResponseEntity.ok(updatedWorkshop);
    }

    /* Delete */
    @DeleteMapping("/{workshopId}")
    public ResponseEntity<String> deleteWorkshop(@PathVariable int workshopId) {
        workshopService.deleteWorkshopById(workshopId);
        
        return ResponseEntity.ok("Workshop deleted successfully, was " + workshopId);
    }

    /* Vehicle related ops */
    @GetMapping("/{workshopId}/vehicles")
    public ResponseEntity<List<VehicleModel>> getWorkshopVehicles(@PathVariable int workshopId) {
        List<VehicleModel> vehicles = workshopService.getWorkshopVehicles(workshopId);

        return ResponseEntity.ok(vehicles);
    }
}
