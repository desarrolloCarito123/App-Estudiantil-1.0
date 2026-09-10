package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Alumno;
import com.app.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    //  Obtener todos los alumnos
    
    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    //  Buscar un alumno por su ID
    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    //  Guardar o Actualizar un alumno
    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    //  Eliminar un alumno por su ID
    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }

    //  Método personalizado 1: Buscar por Carrera
    public List<Alumno> buscarPorCarrera(String carrera) {
        return alumnoRepository.findByCarreraContainingIgnoreCase(carrera);
    }

    //  Método personalizado 2: Buscar por RUT
    public Optional<Alumno> buscarPorRut(String rut) {
        return alumnoRepository.findByRut(rut);
    }
}