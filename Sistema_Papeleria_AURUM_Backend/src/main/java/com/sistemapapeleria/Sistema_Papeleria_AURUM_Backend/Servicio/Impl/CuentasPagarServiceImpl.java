package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasPagarService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CuentasPagarRepository;

@Service
public class CuentasPagarServiceImpl implements CuentasPagarService {

    private final CuentasPagarRepository cuentasPagarRepository;

    @Autowired
    public CuentasPagarServiceImpl(CuentasPagarRepository cuentasPagarRepository) {
        this.cuentasPagarRepository = cuentasPagarRepository;
    }

    @Override
    public CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO cuentaDTO) {
        CuentasPagar cuenta = new CuentasPagar();
        cuenta.setProveedorId(cuentaDTO.getProveedorId());
        cuenta.setMonto(cuentaDTO.getMonto());
        cuenta.setEstado(cuentaDTO.getEstado());
        
        CuentasPagar saved = cuentasPagarRepository.save(cuenta);
        
        return mapToDTO(saved);
    }

    @Override
    public List<CuentasPagarDTO> getAllCuentasPagar() {
        return cuentasPagarRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CuentasPagarDTO getCuentaPagarById(Long id) {
        return cuentasPagarRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public void deleteCuentaPagar(Long id) {
        cuentasPagarRepository.deleteById(id);
    }

    private CuentasPagarDTO mapToDTO(CuentasPagar entity) {
        CuentasPagarDTO dto = new CuentasPagarDTO();
        dto.setId(entity.getId());
        dto.setProveedorId(entity.getProveedorId());
        dto.setMonto(entity.getMonto());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}