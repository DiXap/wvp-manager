package com.solera.wvpmanager.services;

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
    public void createWorkshop(String name, String email) {
    }


    /* Read */


    /* Update */


    /* Delete */
}
