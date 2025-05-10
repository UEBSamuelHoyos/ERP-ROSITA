package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProveedoresDTO;
import java.util.List;

public interface ProveedoresService {
    ProveedoresDTO saveProveedor(ProveedoresDTO proveedorDTO);
    List<ProveedoresDTO> getAllProveedores();
    ProveedoresDTO getProveedorById(Long id);
    void deleteProveedor(Long id);
}
