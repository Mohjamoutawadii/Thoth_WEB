package com.dentsbackend.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professor extends User {
    private String grade;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Groupe> groupes;
}
