package com.dentsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Groupe;
import com.dentsbackend.entities.Professor;

import java.util.List;
import com.dentsbackend.entities.Student;



@Repository
public interface GroupeRepository extends JpaRepository<Groupe,Long>{
  List<Groupe> findByProfessor(Professor professor);
  public Groupe findByCode(String code);
  public List<Groupe> findByYear(String year);

}
