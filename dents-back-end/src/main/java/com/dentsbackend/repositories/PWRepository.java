package com.dentsbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dentsbackend.entities.Groupe;
import com.dentsbackend.entities.PW;

@Repository
public interface PWRepository extends JpaRepository<PW,Long>{
    public List<PW> findByGroupesCode(String code);
    public List<PW> findByGroupes(Groupe groupe);
}
