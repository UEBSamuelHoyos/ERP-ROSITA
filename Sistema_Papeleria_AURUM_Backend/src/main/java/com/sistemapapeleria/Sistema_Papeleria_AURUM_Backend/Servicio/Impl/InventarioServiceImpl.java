package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.InventarioDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.InventarioService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductosRepository productosRepository;

    @Autowired
    public InventarioServiceImpl(InventarioRepository inventarioRepository, ProductosRepository productosRepository) {
        this.inventarioRepository = inventarioRepository;
        this.productosRepository = productosRepository;
    }

    @Override
    public InventarioDTO saveInventario(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setCategoria(dto.getCategoria());
        inventario.setFechaIngreso(dto.getFechaIngreso());
        inventario.setNombreProducto(dto.getNombreProducto());
        inventario.setProducto(new Productos(dto.getIdProducto(), "", "", 0, 0, 0)); // Relación con producto
        inventario.setCantidadProducto(dto.getCantidadProducto());

        Inventario saved = inventarioRepository.save(inventario);
        return mapToDTO(saved);
    }

    @Override
    public List<InventarioDTO> getAllInventario() {
        // Obtener todos los productos
        List<Productos> productos = productosRepository.findAll();

        // Crear una lista de InventarioDTO basada en los productos
        return productos.stream().map(producto -> {
            Inventario inventario = inventarioRepository.findByProducto_Id(producto.getId()).orElse(null);

            InventarioDTO dto = new InventarioDTO();
            dto.setCategoria(producto.getCategoria());
            dto.setNombreProducto(producto.getNombre());
            dto.setIdProducto(producto.getId());
            dto.setCantidadProducto(inventario != null ? inventario.getCantidadProducto() : producto.getStock());
            dto.setFechaIngreso(inventario != null ? inventario.getFechaIngreso() : null);

            return dto;
        }).collect(Collectors.toList());
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
        dto.setCategoria(entity.getCategoria()); // Asignar la categoría
        dto.setFechaIngreso(entity.getFechaIngreso()); // Asignar la fecha de ingreso
        dto.setNombreProducto(entity.getNombreProducto()); // Asignar el nombre del producto
        dto.setIdProducto(entity.getProducto().getId()); // Asignar el ID del producto
        dto.setCantidadProducto(entity.getCantidadProducto()); // Asignar la cantidad
        return dto;
    }
}
