package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Devoluciones;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionesDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DevolucionesService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.DevolucionesRepository;

@Service
public class DevolucionesServiceImpl implements DevolucionesService {

    private final DevolucionesRepository devolucionesRepository;

    @Autowired
    public DevolucionesServiceImpl(DevolucionesRepository devolucionesRepository) {
        this.devolucionesRepository = devolucionesRepository;
    }

    @Override
    public DevolucionesDTO saveDevolucion(DevolucionesDTO dto) {
        Devoluciones entity = new Devoluciones();
        entity.setFacturaId(dto.getFacturaId());
        entity.setProductoId(dto.getProductoId());
        entity.setCantidad(dto.getCantidad());
        entity.setMotivo(dto.getMotivo());

        Devoluciones saved = devolucionesRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<DevolucionesDTO> getAllDevoluciones() {
        return devolucionesRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public DevolucionesDTO getDevolucionById(Long id) {
        return devolucionesRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public List<DevolucionesDTO> getDevolucionesByFacturaId(Long facturaId) {
        return devolucionesRepository.findAll().stream()
            .filter(d -> d.getFacturaId().equals(facturaId))
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteDevolucion(Long id) {
        devolucionesRepository.deleteById(id);
    }

    private DevolucionesDTO mapToDTO(Devoluciones entity) {
        DevolucionesDTO dto = new DevolucionesDTO();
        dto.setId(entity.getId());
        dto.setFacturaId(entity.getFacturaId());
        dto.setProductoId(entity.getProductoId());
        dto.setCantidad(entity.getCantidad());
        dto.setMotivo(entity.getMotivo());
        return dto;
    }
}
