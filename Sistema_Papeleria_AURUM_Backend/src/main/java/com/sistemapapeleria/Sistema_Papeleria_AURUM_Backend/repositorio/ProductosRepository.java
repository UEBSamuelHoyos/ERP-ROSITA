package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {
}
