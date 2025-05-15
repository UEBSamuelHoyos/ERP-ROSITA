package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.FacturaDTO;
import java.util.List;

public interface FacturaService {
    FacturaDTO saveFactura(FacturaDTO facturaDTO);
    List<FacturaDTO> getAllFacturas();
    FacturaDTO getFacturaById(Long id);
    void deleteFactura(Long id);
}
