package com.solera.wvpmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solera.wvpmanager.models.VehicleModel;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Integer> {

}
