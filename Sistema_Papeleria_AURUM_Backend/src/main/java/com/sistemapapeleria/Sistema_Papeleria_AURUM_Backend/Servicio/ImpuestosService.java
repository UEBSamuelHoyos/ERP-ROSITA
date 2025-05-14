package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ImpuestosDTO;

public interface ImpuestosService {
    ImpuestosDTO saveImpuesto(ImpuestosDTO impuestoDTO);
    List<ImpuestosDTO> getAllImpuestos();
    ImpuestosDTO getImpuestoById(Long id);
    void deleteImpuesto(Long id);
}
