package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductosDTO;

public interface ProductosService {
    ProductosDTO saveProducto(ProductosDTO productoDTO);
    List<ProductosDTO> getAllProductos();
    ProductosDTO getProductoById(Long id);
    void deleteProducto(Long id);
}
