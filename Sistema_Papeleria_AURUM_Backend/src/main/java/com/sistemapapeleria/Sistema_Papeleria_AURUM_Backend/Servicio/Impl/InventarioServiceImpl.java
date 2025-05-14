package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.InventarioDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.InventarioService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioServiceImpl(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public InventarioDTO saveInventario(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setCategoria(dto.getCategoria());
        inventario.setFechaIngreso(dto.getFechaIngreso());
        inventario.setNombreProducto(dto.getNombreProducto());
        inventario.setIdProducto(dto.getIdProducto());
        inventario.setCantidadProducto(dto.getCantidadProducto());

        Inventario saved = inventarioRepository.save(inventario);
        return mapToDTO(saved);
    }

    @Override
    public List<InventarioDTO> getAllInventario() {
        return inventarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventarioDTO getInventarioById(Long id) {
        return inventarioRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteInventario(Long id) {
        inventarioRepository.deleteById(id);
    }

    private InventarioDTO mapToDTO(Inventario entity) {
        InventarioDTO dto = new InventarioDTO();
        dto.setCategoria(entity.getCategoria());
        dto.setFechaIngreso(entity.getFechaIngreso());
        dto.setNombreProducto(entity.getNombreProducto());
        dto.setIdProducto(entity.getIdProducto());
        dto.setCantidadProducto(entity.getCantidadProducto());
        return dto;
    }
}
