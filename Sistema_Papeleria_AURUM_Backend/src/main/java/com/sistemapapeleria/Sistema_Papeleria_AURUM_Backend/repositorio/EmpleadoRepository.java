package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
