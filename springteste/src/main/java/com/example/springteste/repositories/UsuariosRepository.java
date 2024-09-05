package com.example.springteste.repositories;

import com.example.springteste.models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
}
