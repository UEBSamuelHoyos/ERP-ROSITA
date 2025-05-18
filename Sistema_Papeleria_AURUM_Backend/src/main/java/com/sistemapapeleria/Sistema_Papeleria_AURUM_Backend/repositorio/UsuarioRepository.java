package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}

