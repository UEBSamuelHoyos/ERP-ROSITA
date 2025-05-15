package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProductosService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService {

    private final ProductosRepository productosRepository;

    @Autowired
    public ProductosServiceImpl(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public ProductosDTO saveProducto(ProductosDTO dto) {
        Productos producto = new Productos();
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());

        Productos saved = productosRepository.save(producto);
        return mapToDTO(saved);
    }

    @Override
    public List<ProductosDTO> getAllProductos() {
        return productosRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Buscar productos por nombre (contiene)
    public List<ProductosDTO> buscarPorNombre(String nombre) {
        return productosRepository.findByNombreContainingIgnoreCase(nombre)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Buscar productos por categoría exacta (sin importar mayúsculas)
    public List<ProductosDTO> buscarPorCategoria(String categoria) {
        return productosRepository.findByCategoriaIgnoreCase(categoria)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Buscar productos con stock menor a cierto límite
    public List<ProductosDTO> buscarStockBajo(int limite) {
        return productosRepository.findByStockLessThan(limite)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Buscar productos por rango de precio
    public List<ProductosDTO> buscarPorRangoPrecio(double min, double max) {
        return productosRepository.findByPrecioVentaBetween(min, max)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Buscar productos con al menos X unidades en stock
    public List<ProductosDTO> buscarDisponibles(int minStock) {
        return productosRepository.findByStockGreaterThanEqual(minStock)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductosDTO getProductoById(Long id) {
        return productosRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteProducto(Long id) {
        productosRepository.deleteById(id);
    }

    public ProductosDTO updateProducto(Long id, ProductosDTO dto) {
        return productosRepository.findById(id).map(producto -> {
            producto.setCategoria(dto.getCategoria());
            producto.setNombre(dto.getNombre());
            producto.setPrecioCompra(dto.getPrecioCompra());
            producto.setPrecioVenta(dto.getPrecioVenta());
            producto.setStock(dto.getStock());

            Productos actualizado = productosRepository.save(producto);
            return mapToDTO(actualizado);
        }).orElse(null);
    }

    public ProductosDTO reducirStock(Long id, int cantidad) {
        return productosRepository.findById(id).map(producto -> {
            producto.reducirStock(cantidad);
            Productos actualizado = productosRepository.save(producto);
            return mapToDTO(actualizado);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    private ProductosDTO mapToDTO(Productos entity) {
        ProductosDTO dto = new ProductosDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCategoria(entity.getCategoria());
        dto.setPrecioCompra(entity.getPrecioCompra());
        dto.setPrecioVenta(entity.getPrecioVenta());
        dto.setStock(entity.getStock());
        return dto;
    }
}
