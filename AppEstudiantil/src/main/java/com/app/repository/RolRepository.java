package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Busca un rol por su nombre (ej: "ROLE_ADMIN")
	
    Optional<Rol> findByNombre(String nombre);
}