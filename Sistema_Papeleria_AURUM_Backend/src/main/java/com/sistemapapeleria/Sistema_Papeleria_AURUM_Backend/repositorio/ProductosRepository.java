package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {

    // Buscar productos por nombre (contiene)
    List<Productos> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos por categoría
    List<Productos> findByCategoriaIgnoreCase(String categoria);

    // Buscar productos con stock menor al valor dado
    List<Productos> findByStockLessThan(Integer stock);

    // Buscar productos por rango de precio
    List<Productos> findByPrecioVentaBetween(Double min, Double max);

    // Buscar productos con stock disponible
    List<Productos> findByStockGreaterThanEqual(Integer stock);
}
