package com.app.controller;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.model.Rol;
import com.app.model.Usuario;
import com.app.repository.RolRepository;
import com.app.repository.UsuarioRepository;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Muestra la pantalla de Login
    
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    // Muestra el formulario de Registro
    
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesa el formulario de Registro
    
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            model.addAttribute("error", "El nombre de usuario ya está registrado.");
            return "registro";
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "registro";
        }

        // Encriptar la contraseña con BCrypt
        
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar el rol por defecto (ROLE_USER)
        
        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
                .orElseGet(() -> rolRepository.save(new Rol("ROLE_USER")));
        
        usuario.setRoles(Collections.singleton(rolUser));

        usuarioRepository.save(usuario);

        return "redirect:/login?registrado";
    }
}