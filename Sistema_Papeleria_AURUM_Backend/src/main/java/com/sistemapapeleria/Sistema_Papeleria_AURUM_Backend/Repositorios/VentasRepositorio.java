package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Repositorios;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepositorio extends JpaRepository<Ventas, Long> {
}
