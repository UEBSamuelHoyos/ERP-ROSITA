package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProveedoresDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CompraProveedorRequest;
import java.util.List;

public interface ProveedoresService {
    ProveedoresDTO saveProveedor(ProveedoresDTO dto);
    List<ProveedoresDTO> getAllProveedores();
    ProveedoresDTO getProveedorById(Long id);
    void deleteProveedor(Long id);
    void comprarProductos(Long proveedorId, CompraProveedorRequest request);
}
