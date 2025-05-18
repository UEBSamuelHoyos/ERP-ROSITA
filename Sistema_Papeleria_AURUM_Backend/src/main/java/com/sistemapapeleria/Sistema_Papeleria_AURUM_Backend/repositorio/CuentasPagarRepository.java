package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentasPagarRepository extends JpaRepository<CuentasPagar, Long> {
    List<CuentasPagar> findByProveedorId(Long proveedorId);
}