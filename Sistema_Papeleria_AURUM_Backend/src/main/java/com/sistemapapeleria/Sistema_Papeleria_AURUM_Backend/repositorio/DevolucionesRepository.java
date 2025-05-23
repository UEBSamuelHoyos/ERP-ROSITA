package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Devoluciones;

@Repository
public interface DevolucionesRepository extends JpaRepository<Devoluciones, Long> {
}
