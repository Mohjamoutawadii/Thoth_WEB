package com.dentsbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String objectif;
    private String docs;
    @ManyToOne
    private Tooth tooth;
    @ManyToMany
    @JsonIgnore
    private List<Groupe> groupes;

}
