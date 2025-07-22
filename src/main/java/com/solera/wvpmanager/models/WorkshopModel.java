package com.solera.wvpmanager.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkshopModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String name;
   private String email; 

   // TODO. Considerar regresar a LAZY y manejar la carga de vehiculos desde el repo conun query
   @OneToMany(mappedBy = "workshop", fetch = FetchType.EAGER) // Cascade?
   private List<VehicleModel> vehicles = new ArrayList<>();
}
