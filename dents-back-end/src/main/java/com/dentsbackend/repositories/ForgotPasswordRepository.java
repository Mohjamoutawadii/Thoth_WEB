package com.dentsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.ForgotPasswordToken;



@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, Long>{

	ForgotPasswordToken findByToken(String token);
	
}
