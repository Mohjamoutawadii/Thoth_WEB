package com.dentsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
  
}
