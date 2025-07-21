package com.solera.wvpmanager.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_part;
    private String parts_name;
    private int part_num;
    private String brand_part;
}
