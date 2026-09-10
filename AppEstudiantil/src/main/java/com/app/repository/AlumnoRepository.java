package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Alumno;

@Repository

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    // Método de búsqueda personalizada  Filtrar por carrera
	
    List<Alumno> findByCarreraContainingIgnoreCase(String carrera);

    // Método de búsqueda personalizada  Buscar por RUT
    
    Optional<Alumno> findByRut(String rut);
}