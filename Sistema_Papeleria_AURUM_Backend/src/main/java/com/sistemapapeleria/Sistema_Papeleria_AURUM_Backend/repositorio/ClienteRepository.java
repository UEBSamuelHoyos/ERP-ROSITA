package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar por cédula exacta
    Optional<Cliente> findByCedula(String cedula);

    // Buscar por nombre parcial (sin importar mayúsculas)
    List<Cliente> findByNombreCompletoContainingIgnoreCase(String nombre);
}
