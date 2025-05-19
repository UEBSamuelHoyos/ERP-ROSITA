package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO cuentaDTO) {
        CuentasPagar cuenta = new CuentasPagar();
        cuenta.setProveedorId(cuentaDTO.getProveedorId());
        cuenta.setMonto(cuentaDTO.getMonto());
        cuenta.setEstado(cuentaDTO.getEstado());
        
=======
    public CuentasPagarDTO saveCuentaPagar(CuentasPagarDTO dto) {
        CuentasPagar cuenta = mapToEntity(dto);
>>>>>>> Stashed changes
        CuentasPagar saved = cuentasPagarRepository.save(cuenta);
        
        return mapToDTO(saved);
    }

    @Override
    public List<CuentasPagarDTO> getAllCuentasPagar() {
<<<<<<< Updated upstream
        return cuentasPagarRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
=======
        return cuentasPagarRepository.findAll()
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
>>>>>>> Stashed changes
    }

    @Override
    public CuentasPagarDTO getCuentaPagarById(Long id) {
        return cuentasPagarRepository.findById(id)
<<<<<<< Updated upstream
            .map(this::mapToDTO)
            .orElse(null);
=======
                .map(this::mapToDTO)
                .orElse(null);
>>>>>>> Stashed changes
    }

    @Override
    public void deleteCuentaPagar(Long id) {
        cuentasPagarRepository.deleteById(id);
    }

<<<<<<< Updated upstream
=======
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
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuentasPagarDTO marcarComoPagada(Long id) {
        return cuentasPagarRepository.findById(id).map(cuenta -> {
            cuenta.setEstado("PAGADO");
            cuentasPagarRepository.save(cuenta);
            return mapToDTO(cuenta);
        }).orElse(null);
    }

    private CuentasPagar mapToEntity(CuentasPagarDTO dto) {
        CuentasPagar entity = new CuentasPagar();
        entity.setProveedorId(dto.getProveedorId());
        entity.setMonto(dto.getMonto());
        entity.setEstado(dto.getEstado());
        return entity;
    }

>>>>>>> Stashed changes
    private CuentasPagarDTO mapToDTO(CuentasPagar entity) {
        CuentasPagarDTO dto = new CuentasPagarDTO();
        dto.setId(entity.getId());
        dto.setProveedorId(entity.getProveedorId());
        dto.setMonto(entity.getMonto());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}
