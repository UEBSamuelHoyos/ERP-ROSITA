package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;

public interface VentasService {
    VentasDTO saveVenta(VentasDTO ventaDTO);
    List<VentasDTO> getAllVentas();
    VentasDTO getVentaById(Long id);
    void deleteVenta(Long id);
}
