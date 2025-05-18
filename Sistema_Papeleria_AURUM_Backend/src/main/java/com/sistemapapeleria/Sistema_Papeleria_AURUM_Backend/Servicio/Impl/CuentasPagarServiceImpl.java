package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasPagarService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CuentasPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentasPagarServiceImpl implements CuentasPagarService {

    private final CuentasPagarRepository cuentasPagarRepository;

    @Autowired
    public CuentasPagarServiceImpl(CuentasPagarRepository cuentasPagarRepository) {
        this.cuentasPagarRepository = cuentasPagarRepository;
    }

    @Override
    public CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO dto) {
        CuentasPagar cuenta = new CuentasPagar();
        cuenta.setProveedorId(dto.getProveedorId());
        cuenta.setMonto(dto.getMonto());
        cuenta.setEstado(dto.getEstado());
        CuentasPagar saved = cuentasPagarRepository.save(cuenta);
        return mapToDTO(saved);
    }

    @Override
    public List<CuentasPagarDTO> getAllCuentasPagar() {
        return cuentasPagarRepository.findAll().stream()
            .map(this::mapToDTO)
            .toList();
    }

    @Override
    public CuentasPagarDTO getCuentaPagarById(Long id) {
        return cuentasPagarRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public void deleteCuentaPagar(Long id) {
        cuentasPagarRepository.deleteById(id);
    }

    @Override
    public CuentasPagarDTO updateCuentaPagar(Long id, CuentasPagarDTO dto) {
        return cuentasPagarRepository.findById(id).map(cuenta -> {
            cuenta.setProveedorId(dto.getProveedorId());
            cuenta.setMonto(dto.getMonto());
            cuenta.setEstado(dto.getEstado());
            CuentasPagar actualizado = cuentasPagarRepository.save(cuenta);
            return mapToDTO(actualizado);
        }).orElse(null);
    }

    @Override
    public List<CuentasPagarDTO> getCuentasByProveedorId(Long proveedorId) {
        return cuentasPagarRepository.findByProveedorId(proveedorId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CuentasPagarDTO marcarComoPagada(Long id) {
        return cuentasPagarRepository.findById(id).map(cuenta -> {
            cuenta.setEstado("PAGADO");
            cuentasPagarRepository.save(cuenta);
            return mapToDTO(cuenta);
        }).orElse(null);
    }

    private CuentasPagarDTO mapToDTO(CuentasPagar cuenta) {
        CuentasPagarDTO dto = new CuentasPagarDTO();
        dto.setId(cuenta.getId());
        dto.setProveedorId(cuenta.getProveedorId());
        dto.setMonto(cuenta.getMonto());
        dto.setEstado(cuenta.getEstado());
        return dto;
    }
}