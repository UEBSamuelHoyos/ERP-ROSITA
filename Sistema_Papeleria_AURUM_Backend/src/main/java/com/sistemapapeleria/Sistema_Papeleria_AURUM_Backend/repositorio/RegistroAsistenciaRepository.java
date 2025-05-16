package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.RegistroAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistroAsistenciaRepository extends JpaRepository<RegistroAsistencia, Long> {
    List<RegistroAsistencia> findByEmpleado_Id(Long empleadoId);
}
