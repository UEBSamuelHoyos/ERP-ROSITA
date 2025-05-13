package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.InventarioDTO;
import java.util.List;

public interface InventarioService {
    InventarioDTO saveInventario(InventarioDTO inventarioDTO);
    List<InventarioDTO> getAllInventario();
    InventarioDTO getInventarioById(Long id);
    void deleteInventario(Long id);
}
