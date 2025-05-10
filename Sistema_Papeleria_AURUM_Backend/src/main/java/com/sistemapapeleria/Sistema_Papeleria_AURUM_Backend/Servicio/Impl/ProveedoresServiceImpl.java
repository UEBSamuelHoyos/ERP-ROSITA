package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Proveedores;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProveedoresDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProveedoresRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProveedoresService;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    @Autowired
    public ProveedoresServiceImpl(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public ProveedoresDTO saveProveedor(ProveedoresDTO dto) {
        Proveedores proveedor = new Proveedores();
        proveedor.setNombreCompleto(dto.getNombreCompleto());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCedula(dto.getCedula());

        Proveedores saved = proveedoresRepository.save(proveedor);
        return mapToDTO(saved);
    }

    @Override
    public List<ProveedoresDTO> getAllProveedores() {
        return proveedoresRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedoresDTO getProveedorById(Long id) {
        return proveedoresRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteProveedor(Long id) {
        proveedoresRepository.deleteById(id);
    }

    private ProveedoresDTO mapToDTO(Proveedores entity) {
        ProveedoresDTO dto = new ProveedoresDTO();
        dto.setId(entity.getId());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        dto.setCedula(entity.getCedula());
        return dto;
    }
}
