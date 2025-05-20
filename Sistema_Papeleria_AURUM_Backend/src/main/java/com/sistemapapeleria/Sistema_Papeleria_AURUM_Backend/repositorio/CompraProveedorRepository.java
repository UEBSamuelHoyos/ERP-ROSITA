package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CompraProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraProveedorRepository extends JpaRepository<CompraProveedor, Long> {
    // Puedes agregar métodos de consulta personalizados si los necesitas
}
