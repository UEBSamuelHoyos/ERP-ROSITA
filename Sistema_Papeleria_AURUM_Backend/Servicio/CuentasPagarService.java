package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;

public interface CuentasPagarService {
    CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO dto);
    List<CuentasPagarDTO> getAllCuentasPagar();
    CuentasPagarDTO getCuentaPagarById(Long id);
    void deleteCuentaPagar(Long id);
    CuentasPagarDTO updateCuentaPagar(Long id, CuentasPagarDTO dto);
    List<CuentasPagarDTO> getCuentasByProveedorId(Long proveedorId);
    CuentasPagarDTO marcarComoPagada(Long id);
    boolean existeCuentaPendienteParaProveedor(Long proveedorId, Double monto, String descripcion);
}