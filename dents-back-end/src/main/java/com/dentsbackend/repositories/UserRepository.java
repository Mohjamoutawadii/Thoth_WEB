package com.dentsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  User findByUserName(String userName);
  User findByEmail(String email);
}
