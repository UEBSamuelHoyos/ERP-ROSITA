package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;

public interface CuentasPagarService {
    CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO cuentaDTO);
    List<CuentasPagarDTO> getAllCuentasPagar();
    CuentasPagarDTO getCuentaPagarById(Long id);
    void deleteCuentaPagar(Long id);
}