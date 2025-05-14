package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.InventarioDTO;

public interface InventarioService {
    InventarioDTO saveInventario(InventarioDTO inventarioDTO);
    List<InventarioDTO> getAllInventario();
    InventarioDTO getInventarioById(Long id);
    void deleteInventario(Long id);
}
