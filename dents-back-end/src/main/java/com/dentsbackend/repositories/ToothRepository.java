package com.dentsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Tooth;

@Repository
public interface ToothRepository extends JpaRepository<Tooth,Long>{
    
}
