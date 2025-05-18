package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCedula(String cedula);

    List<Cliente> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
}
