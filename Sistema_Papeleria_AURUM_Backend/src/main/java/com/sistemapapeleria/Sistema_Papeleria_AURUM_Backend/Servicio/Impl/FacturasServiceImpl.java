package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Facturas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.FacturasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.FacturasService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.FacturasRepository;

@Service
public class FacturasServiceImpl implements FacturasService {

    private final FacturasRepository facturasRepository;

    @Autowired
    public FacturasServiceImpl(FacturasRepository facturasRepository) {
        this.facturasRepository = facturasRepository;
    }

    @Override
    public FacturasDTO saveFactura(FacturasDTO dto) {
        Facturas factura = new Facturas();
        factura.setClienteId(dto.getClienteId());
        factura.setFecha(dto.getFecha());
        factura.setTotal(dto.getTotal());
        factura.setEstado(dto.getEstado());

        Facturas saved = facturasRepository.save(factura);
        return mapToDTO(saved);
    }

    @Override
    public List<FacturasDTO> getAllFacturas() {
        return facturasRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FacturasDTO getFacturaById(Long id) {
        return facturasRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteFactura(Long id) {
        facturasRepository.deleteById(id);
    }

    private FacturasDTO mapToDTO(Facturas entity) {
        FacturasDTO dto = new FacturasDTO();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getClienteId());
        dto.setFecha(entity.getFecha());
        dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}
