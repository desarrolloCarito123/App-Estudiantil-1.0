package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Alumno;
import com.app.service.AlumnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {

    private final AlumnoService alumnoService;

    // Conectamos el servicio 
    
    public AlumnoRestController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    //  GET: Obtener todos los alumnos
    
    @GetMapping
    public ResponseEntity<List<Alumno>> listarTodos() {
        return ResponseEntity.ok(alumnoService.listarTodos());
    }

    //  GET: Buscar un alumno específico por su rut
    
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> buscarPorId(@PathVariable Long id) {
        return alumnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  GET: Buscar por Carrera (método personalizado 1)
    
    @GetMapping("/buscar/carrera")
    public ResponseEntity<List<Alumno>> buscarPorCarrera(@RequestParam String carrera) {
        return ResponseEntity.ok(alumnoService.buscarPorCarrera(carrera));
    }

    //  GET: Buscar por RUT ( método personalizado 2)
    
    @GetMapping("/buscar/rut")
    public ResponseEntity<Alumno> buscarPorRut(@RequestParam String rut) {
        return alumnoService.buscarPorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  POST: Crear un nuevo alumno
    @PostMapping
    public ResponseEntity<Alumno> crear(@Valid @RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoService.guardar(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    //  PUT: Actualizar un alumno existente
    
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable Long id, @Valid @RequestBody Alumno alumno) {
        return alumnoService.buscarPorId(id).map(existente -> {
            alumno.setId(id);
            return ResponseEntity.ok(alumnoService.guardar(alumno));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 7. DELETE: Eliminar un alumno por su rut
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (alumnoService.buscarPorId(id).isPresent()) {
            alumnoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}