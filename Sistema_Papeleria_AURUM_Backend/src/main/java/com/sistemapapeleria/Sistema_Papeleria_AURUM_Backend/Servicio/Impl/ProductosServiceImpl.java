package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProductosService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;

@Service
public class ProductosServiceImpl implements ProductosService {

    private final ProductosRepository productosRepository;
    private final InventarioRepository inventarioRepository;

    @Autowired
    public ProductosServiceImpl(ProductosRepository productosRepository, InventarioRepository inventarioRepository) {
        this.productosRepository = productosRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public ProductosDTO saveProducto(ProductosDTO dto) {
        Productos producto = new Productos();
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());

        Productos savedProducto = productosRepository.save(producto);

        // Crear un registro en el inventario relacionado con el producto
        Inventario inventario = new Inventario();
        inventario.setProducto(savedProducto);
        inventario.setCategoria(producto.getCategoria());
        inventario.setNombreProducto(producto.getNombre());
        inventario.setFechaIngreso(new Date());
        inventario.setCantidadProducto(producto.getStock());

        inventarioRepository.save(inventario);

        return mapToDTO(savedProducto);
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

    @Override
    public ProductosDTO updateProducto(Long id, ProductosDTO dto) {
        return productosRepository.findById(id).map(producto -> {
            // Validar los datos del DTO
            if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
                throw new RuntimeException("El nombre del producto no puede estar vacío");
            }
            if (dto.getCategoria() == null || dto.getCategoria().isEmpty()) {
                throw new RuntimeException("La categoría del producto no puede estar vacía");
            }
            if (dto.getPrecioCompra() < 0 || dto.getPrecioVenta() < 0) {
                throw new RuntimeException("Los precios no pueden ser negativos");
            }
            if (dto.getStock() < 0) {
                throw new RuntimeException("El stock no puede ser negativo");
            }

            // Actualizar los datos del producto
            producto.setCategoria(dto.getCategoria());
            producto.setNombre(dto.getNombre());
            producto.setPrecioCompra(dto.getPrecioCompra());
            producto.setPrecioVenta(dto.getPrecioVenta());
            producto.setStock(dto.getStock());

            Productos actualizado = productosRepository.save(producto);

            // Sincronizar con el inventario
            sincronizarInventario(actualizado);

            return mapToDTO(actualizado);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    // Método para sincronizar el inventario con el producto
    private void sincronizarInventario(Productos producto) {
        inventarioRepository.findByProducto_Id(producto.getId()).ifPresentOrElse(
            inventario -> {
                // Si el inventario existe, actualizar la cantidad
                inventario.setCantidadProducto(producto.getStock());
                inventario.setCategoria(producto.getCategoria());
                inventario.setNombreProducto(producto.getNombre());
                inventarioRepository.save(inventario);
            },
            () -> {
                // Si el inventario no existe, crearlo
                Inventario nuevoInventario = new Inventario();
                nuevoInventario.setProducto(producto);
                nuevoInventario.setCategoria(producto.getCategoria());
                nuevoInventario.setNombreProducto(producto.getNombre());
                nuevoInventario.setFechaIngreso(new Date());
                nuevoInventario.setCantidadProducto(producto.getStock());
                inventarioRepository.save(nuevoInventario);
            }
        );
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
