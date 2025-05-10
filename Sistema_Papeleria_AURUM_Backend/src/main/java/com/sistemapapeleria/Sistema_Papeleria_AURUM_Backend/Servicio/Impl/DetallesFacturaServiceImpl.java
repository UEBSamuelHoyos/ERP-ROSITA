package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.DetallesFactura;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DetallesFacturaDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DetallesFacturaService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.DetallesFacturaRepository;

@Service
public class DetallesFacturaServiceImpl implements DetallesFacturaService {

    private final DetallesFacturaRepository detallesFacturaRepository;

    @Autowired
    public DetallesFacturaServiceImpl(DetallesFacturaRepository detallesFacturaRepository) {
        this.detallesFacturaRepository = detallesFacturaRepository;
    }

    @Override
    public DetallesFacturaDTO saveDetalleFactura(DetallesFacturaDTO detalleDTO) {
        DetallesFactura detalle = new DetallesFactura();
        detalle.setFacturaId(detalleDTO.getFacturaId());
        detalle.setProductoId(detalleDTO.getProductoId());
        detalle.setCantidad(detalleDTO.getCantidad());
        detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
        
        DetallesFactura saved = detallesFacturaRepository.save(detalle);
        
        return mapToDTO(saved);
    }

    @Override
    public List<DetallesFacturaDTO> getAllDetallesFactura() {
        return detallesFacturaRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public DetallesFacturaDTO getDetalleFacturaById(Long id) {
        return detallesFacturaRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public List<DetallesFacturaDTO> getDetallesByFacturaId(Long facturaId) {
        return detallesFacturaRepository.findAll().stream()
            .filter(detalle -> detalle.getFacturaId().equals(facturaId))
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteDetalleFactura(Long id) {
        detallesFacturaRepository.deleteById(id);
    }

    private DetallesFacturaDTO mapToDTO(DetallesFactura entity) {
        DetallesFacturaDTO dto = new DetallesFacturaDTO();
        dto.setId(entity.getId());
        dto.setFacturaId(entity.getFacturaId());
        dto.setProductoId(entity.getProductoId());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        return dto;
    }
}