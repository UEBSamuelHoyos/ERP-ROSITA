package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionesDTO;

public interface DevolucionesService {
    DevolucionesDTO saveDevolucion(DevolucionesDTO devolucionDTO);
    List<DevolucionesDTO> getAllDevoluciones();
    DevolucionesDTO getDevolucionById(Long id);
    List<DevolucionesDTO> getDevolucionesByFacturaId(Long facturaId);
    void deleteDevolucion(Long id);
}
