package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su nombre de usuario para el Login
	
    Optional<Usuario> findByUsername(String username);

    // Verifica si un nombre de usuario ya existe al registrarse
    
    Boolean existsByUsername(String username);

    // Verifica si un email ya existe 
    
    Boolean existsByEmail(String email);
}