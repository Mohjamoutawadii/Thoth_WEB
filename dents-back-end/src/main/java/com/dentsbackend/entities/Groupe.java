package com.dentsbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String year;
    @ManyToOne
    private Professor professor;

    @OneToMany(mappedBy = "groupe")
    @JsonIgnore
    private List<Student> students;
    
    @ManyToMany(mappedBy = "groupes")
    @JsonIgnore
    private List<PW> pws;

}
