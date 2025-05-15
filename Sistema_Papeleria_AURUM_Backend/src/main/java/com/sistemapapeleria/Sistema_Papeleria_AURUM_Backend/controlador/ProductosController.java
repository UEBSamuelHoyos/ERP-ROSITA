package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProductosService;

@RestController
@RequestMapping("/api/productos") // Asegúrate de que la ruta esté en minúsculas
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde el frontend
public class ProductosController {

    private final ProductosService productosService;

    @Autowired
    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<ProductosDTO> crear(@RequestBody ProductosDTO dto) {
        return ResponseEntity.ok(productosService.saveProducto(dto));
    }

    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<ProductosDTO>> listarTodos() {
        return ResponseEntity.ok(productosService.getAllProductos());
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> buscarPorId(@PathVariable Long id) {
        ProductosDTO dto = productosService.getProductoById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // Actualizar producto por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable("id") Long id, @RequestBody ProductosDTO dto) {
        try {
            ProductosDTO actualizado = productosService.updateProducto(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Eliminar producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productosService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
