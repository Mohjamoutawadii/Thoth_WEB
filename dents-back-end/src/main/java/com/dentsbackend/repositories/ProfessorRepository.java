package com.dentsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Professor;



@Repository
public interface ProfessorRepository extends JpaRepository <Professor,Long> {
   Professor findByUserName(String userName);
}
