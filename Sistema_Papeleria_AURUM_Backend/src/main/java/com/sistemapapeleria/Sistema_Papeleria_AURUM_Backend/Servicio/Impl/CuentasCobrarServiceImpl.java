package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasCobrar;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasCobrarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasCobrarService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CuentasCobrarRepository;

@Service
public class CuentasCobrarServiceImpl implements CuentasCobrarService {

    private final CuentasCobrarRepository cuentasCobrarRepository;
                    
    @Autowired
    public CuentasCobrarServiceImpl(CuentasCobrarRepository cuentasCobrarRepository) {
        this.cuentasCobrarRepository = cuentasCobrarRepository;
    }

    @Override
    public CuentasCobrarDTO saveCuentaCobrar(CuentasCobrarDTO cuentaDTO) {
        CuentasCobrar cuenta = mapToEntity(cuentaDTO);
        CuentasCobrar saved = cuentasCobrarRepository.save(cuenta);
        return mapToDTO(saved);
    }

    @Override
    public List<CuentasCobrarDTO> getAllCuentasCobrar() {
        return cuentasCobrarRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CuentasCobrarDTO getCuentaCobrarById(Long id) {
        return cuentasCobrarRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public void deleteCuentaCobrar(Long id) {
        cuentasCobrarRepository.deleteById(id);
    }

    private CuentasCobrar mapToEntity(CuentasCobrarDTO dto) {
        CuentasCobrar entity = new CuentasCobrar();
        entity.setId(dto.getId());
        entity.setClienteId(dto.getClienteId());
        entity.setMonto(dto.getMonto());
        entity.setEstado(dto.getEstado());
        return entity;
    }

    private CuentasCobrarDTO mapToDTO(CuentasCobrar entity) {
        CuentasCobrarDTO dto = new CuentasCobrarDTO();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getClienteId());
        dto.setMonto(entity.getMonto());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}