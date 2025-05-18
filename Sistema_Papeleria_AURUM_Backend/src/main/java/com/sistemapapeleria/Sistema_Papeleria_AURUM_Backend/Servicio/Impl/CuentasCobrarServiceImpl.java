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

    // 1. Crear una cuenta de cobro
    @Override
    public CuentasCobrarDTO saveCuentaCobrar(CuentasCobrarDTO cuentaDTO) {
        CuentasCobrar cuenta = mapToEntity(cuentaDTO);
        CuentasCobrar saved = cuentasCobrarRepository.save(cuenta);
        return mapToDTO(saved);
    }

    // 2. Listar todas las cuentas de cobro
    @Override
    public List<CuentasCobrarDTO> getAllCuentasCobrar() {
        return cuentasCobrarRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    // 3. Obtener una cuenta por ID
    @Override
    public CuentasCobrarDTO getCuentaCobrarById(Long id) {
        return cuentasCobrarRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    // 4. Eliminar una cuenta por ID
    @Override
    public void deleteCuentaCobrar(Long id) {
        cuentasCobrarRepository.deleteById(id);
    }

    // 5. Actualizar una cuenta por ID
    public CuentasCobrarDTO updateCuentaCobrar(Long id, CuentasCobrarDTO dto) {
        return cuentasCobrarRepository.findById(id).map(cuenta -> {
            cuenta.setClienteId(dto.getClienteId());
            cuenta.setMonto(dto.getMonto());
            cuenta.setEstado(dto.getEstado());
            CuentasCobrar updated = cuentasCobrarRepository.save(cuenta);
            return mapToDTO(updated);
        }).orElse(null);
    }

    // 6. Buscar todas las cuentas por clienteId
    public List<CuentasCobrarDTO> getCuentasByClienteId(Long clienteId) {
        return cuentasCobrarRepository.findByClienteId(clienteId)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    // 7. Marcar una cuenta como pagada
    public CuentasCobrarDTO marcarComoPagada(Long id) {
        return cuentasCobrarRepository.findById(id).map(cuenta -> {
            cuenta.setEstado("PAGADO");
            cuentasCobrarRepository.save(cuenta);
            return mapToDTO(cuenta);
        }).orElse(null);
    }

    // 🔄 Utilidad: Convertir DTO → Entidad
    private CuentasCobrar mapToEntity(CuentasCobrarDTO dto) {
        CuentasCobrar entity = new CuentasCobrar();
        // No asignes el id aquí para nuevas cuentas
        entity.setClienteId(dto.getClienteId());
        entity.setMonto(dto.getMonto());
        entity.setEstado(dto.getEstado());
        return entity;
    }

    // 🔄 Utilidad: Convertir Entidad → DTO
    private CuentasCobrarDTO mapToDTO(CuentasCobrar entity) {
        CuentasCobrarDTO dto = new CuentasCobrarDTO();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getClienteId());
        dto.setMonto(entity.getMonto());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}
