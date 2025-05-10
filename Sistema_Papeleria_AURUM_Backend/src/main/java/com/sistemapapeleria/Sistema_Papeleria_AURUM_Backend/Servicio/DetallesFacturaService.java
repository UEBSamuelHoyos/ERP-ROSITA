package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DetallesFacturaDTO;

public interface DetallesFacturaService {
    DetallesFacturaDTO saveDetalleFactura(DetallesFacturaDTO detalleDTO);
    List<DetallesFacturaDTO> getAllDetallesFactura();
    DetallesFacturaDTO getDetalleFacturaById(Long id);
    List<DetallesFacturaDTO> getDetallesByFacturaId(Long facturaId);
    void deleteDetalleFactura(Long id);
}