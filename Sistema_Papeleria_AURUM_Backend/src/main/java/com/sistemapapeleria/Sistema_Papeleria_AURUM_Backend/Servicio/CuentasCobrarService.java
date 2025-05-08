package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasCobrarDTO;

public interface CuentasCobrarService {
    CuentasCobrarDTO saveCuentaCobrar(CuentasCobrarDTO cuentaDTO);
    List<CuentasCobrarDTO> getAllCuentasCobrar();
    CuentasCobrarDTO getCuentaCobrarById(Long id);
    void deleteCuentaCobrar(Long id);
}