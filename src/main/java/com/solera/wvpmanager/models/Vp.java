package com.solera.wvpmanager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* This class represents the join table between parts and vehicles 
 * It contains the IDs of both parts and vehicles, allowing us to associate them.
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vp {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) 
    private int id_vp;

    @ManyToOne
    @JoinColumn(name = "parts_model_id")
    private PartsModel partsModel;

    @ManyToOne
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;

    
}
