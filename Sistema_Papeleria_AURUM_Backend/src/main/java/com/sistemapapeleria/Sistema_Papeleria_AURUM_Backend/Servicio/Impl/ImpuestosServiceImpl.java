package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Impuestos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ImpuestosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ImpuestosService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ImpuestosRepository;

@Service
public class ImpuestosServiceImpl implements ImpuestosService {

    private final ImpuestosRepository impuestosRepository;

    @Autowired
    public ImpuestosServiceImpl(ImpuestosRepository impuestosRepository) {
        this.impuestosRepository = impuestosRepository;
    }

    @Override
    public ImpuestosDTO saveImpuesto(ImpuestosDTO dto) {
        Impuestos impuesto = new Impuestos();
        impuesto.setCantidadVentas(dto.getCantidadVentas());
        impuesto.setPorcentajeImpuesto(dto.getPorcentajeImpuesto());
        impuesto.setTotalImpuesto(dto.getTotalImpuesto());
        impuesto.setAnioFiscal(dto.getAnioFiscal());

        Impuestos saved = impuestosRepository.save(impuesto);
        return mapToDTO(saved);
    }

    @Override
    public List<ImpuestosDTO> getAllImpuestos() {
        return impuestosRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImpuestosDTO getImpuestoById(Long id) {
        return impuestosRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteImpuesto(Long id) {
        impuestosRepository.deleteById(id);
    }

    private ImpuestosDTO mapToDTO(Impuestos entity) {
        ImpuestosDTO dto = new ImpuestosDTO();
        dto.setCantidadVentas(entity.getCantidadVentas());
        dto.setPorcentajeImpuesto(entity.getPorcentajeImpuesto());
        dto.setTotalImpuesto(entity.getTotalImpuesto());
        dto.setAnioFiscal(entity.getAnioFiscal());
        return dto;
    }
}
