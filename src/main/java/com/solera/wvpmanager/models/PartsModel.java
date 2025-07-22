package com.solera.wvpmanager.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*This class represents the entity of parts in database
 * With lombok annotations we can avoid boilerplate code like getters, setters, constructors, etc.
 * The id_part is the primary key of the entity and is auto-generated
 * We use @OneToMany to establish a relationship with the VehicleModel class trough the Vp class 
 * Vp class is a join table that connects PartsModel and VehicleModel
*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_part;

    private String parts_name;
    private Integer part_num;
    private String brand_part;

    


    @OneToMany(mappedBy = "partsModel")
    @JsonIgnore
    private List<Vp> vehicles;

    public PartsModel(String parts_name, Integer part_num, String brand_part) {
        this.parts_name = parts_name;
        this.part_num = part_num;
        this.brand_part = brand_part;
    }



}
