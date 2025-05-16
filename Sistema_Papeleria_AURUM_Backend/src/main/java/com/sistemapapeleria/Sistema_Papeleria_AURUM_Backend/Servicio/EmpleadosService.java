package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.EmpleadosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.RegistroAsistencia;

public interface EmpleadosService {
    EmpleadosDTO saveEmpleado(EmpleadosDTO dto);
    List<EmpleadosDTO> getAllEmpleados();
    EmpleadosDTO getEmpleadoById(Long id);
    void deleteEmpleado(Long id);
    void registrarAsistencia(Long empleadoId);
    List<RegistroAsistencia> getAsistencias(Long empleadoId);
}
