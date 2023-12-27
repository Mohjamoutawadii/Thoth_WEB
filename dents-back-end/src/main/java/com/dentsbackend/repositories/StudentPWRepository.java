package com.dentsbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Student;
import com.dentsbackend.entities.StudentPW;
import com.dentsbackend.entities.StudentPWPK;

@Repository
public interface StudentPWRepository extends JpaRepository<StudentPW,StudentPWPK>{
    @Query("SELECT spw FROM StudentPW spw WHERE spw.id.student_id = :studentId")
    List<StudentPW> findByStudentId(@Param("studentId") Long studentId);
}
