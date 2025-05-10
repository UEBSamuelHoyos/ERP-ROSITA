package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.FacturasDTO;

public interface FacturasService {
    FacturasDTO saveFactura(FacturasDTO facturaDTO);
    List<FacturasDTO> getAllFacturas();
    FacturasDTO getFacturaById(Long id);
    void deleteFactura(Long id);
}
