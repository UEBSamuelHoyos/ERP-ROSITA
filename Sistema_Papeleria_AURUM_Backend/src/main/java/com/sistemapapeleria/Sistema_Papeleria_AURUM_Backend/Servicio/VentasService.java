package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import java.util.List;

public interface VentasService {
    VentasDTO saveVenta(VentasDTO ventaDTO);
    List<VentasDTO> getAllVentas();
    VentasDTO getVentaById(Long id);
    void deleteVenta(Long id);
}
