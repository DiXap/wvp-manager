package com.solera.wvpmanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solera.wvpmanager.models.WorkshopModel;
import com.solera.wvpmanager.services.WorkshopService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
