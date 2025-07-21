package com.solera.wvpmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solera.wvpmanager.models.WorkshopModel;

@Repository
public interface WorkshopRepository extends JpaRepository<WorkshopModel, Integer> {
}