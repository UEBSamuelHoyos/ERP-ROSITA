package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ImpuestosDTO;
import java.util.List;

public interface ImpuestosService {
    ImpuestosDTO saveImpuesto(ImpuestosDTO impuestoDTO);
    List<ImpuestosDTO> getAllImpuestos();
    ImpuestosDTO getImpuestoById(Long id);
    void deleteImpuesto(Long id);
}
