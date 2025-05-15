package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturasRepository extends JpaRepository<Facturas, Long> {
    List<Facturas> findByCliente_Id(Long clienteId);
}
