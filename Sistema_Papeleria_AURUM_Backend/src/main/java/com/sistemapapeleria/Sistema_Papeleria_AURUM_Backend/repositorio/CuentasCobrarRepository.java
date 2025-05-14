package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasCobrar;

@Repository
public interface CuentasCobrarRepository extends JpaRepository<CuentasCobrar, Long> {
    List<CuentasCobrar> findByClienteId(Long clienteId);
}
