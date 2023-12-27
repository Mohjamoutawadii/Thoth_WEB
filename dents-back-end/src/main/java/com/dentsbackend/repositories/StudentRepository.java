package com.dentsbackend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Groupe;
import com.dentsbackend.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public Student  findByUserName(String userName);
    public Student findByEmail(String email);
    public List<Student> findByGroupe(Groupe groupe);

}
