package com.example.springteste.repositories;

import com.example.springteste.models.ContasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasRepository extends JpaRepository<ContasModel, Long> {
}
