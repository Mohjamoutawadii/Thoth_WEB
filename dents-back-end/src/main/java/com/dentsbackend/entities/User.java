package com.dentsbackend.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    @Lob
    private byte[] photo;

    boolean isActive;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({ "users" })
    private Set<Role> roles;

}
