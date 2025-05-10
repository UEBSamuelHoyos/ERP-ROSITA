package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.EmpleadosDTO;

public interface EmpleadosService {
    EmpleadosDTO saveEmpleado(EmpleadosDTO dto);
    List<EmpleadosDTO> getAllEmpleados();
    EmpleadosDTO getEmpleadoById(Long id);
    void deleteEmpleado(Long id);
}
