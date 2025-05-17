package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionDTO;
import java.util.List;

public interface DevolucionService {
    DevolucionDTO saveDevolucion(DevolucionDTO dto);
    List<DevolucionDTO> getAllDevoluciones();
    DevolucionDTO getDevolucionById(Long id);
    void deleteDevolucion(Long id);
}
