package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Alumno;
import com.app.service.AlumnoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/alumnos")
public class AlumnoWebController {

    private final AlumnoService alumnoService;

    public AlumnoWebController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Mostrar la lista general de alumnos o filtrar por Carrera/RUT
    
    @GetMapping
    public String listarAlumnos(@RequestParam(required = false) String carrera, 
                                @RequestParam(required = false) String rut, 
                                Model model) {
        if (carrera != null && !carrera.isBlank()) {
            model.addAttribute("alumnos", alumnoService.buscarPorCarrera(carrera));
        } else if (rut != null && !rut.isBlank()) {
            model.addAttribute("alumnos", alumnoService.buscarPorRut(rut)
                    .map(java.util.List::of)
                    .orElse(java.util.Collections.emptyList()));
        } else {
            model.addAttribute("alumnos", alumnoService.listarTodos());
        }
        return "alumnos/lista";
    }

    //  Desplegar el formulario para registrar un nuevo alumno
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "alumnos/formulario";
    }

    //  Desplegar el formulario cargado con los datos de un alumno a editar
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de alumno no válido: " + id));
        model.addAttribute("alumno", alumno);
        return "alumnos/formulario";
    }

    //  Guardar o actualizar los datos procesados en el formulario
    
    @PostMapping("/guardar")
    public String guardarAlumno(@Valid @ModelAttribute("alumno") Alumno alumno, BindingResult result) {
        if (result.hasErrors()) {
            return "alumnos/formulario";
        }
        alumnoService.guardar(alumno);
        return "redirect:/alumnos";
    }

    //  Eliminar un alumno seleccionado
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        alumnoService.eliminar(id);
        return "redirect:/alumnos";
    }
}