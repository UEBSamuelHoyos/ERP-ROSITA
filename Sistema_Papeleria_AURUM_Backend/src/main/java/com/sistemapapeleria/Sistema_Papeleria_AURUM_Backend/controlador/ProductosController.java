package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl.ProductosServiceImpl;

@RestController
@RequestMapping("/api/Productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductosController {

    private final ProductosServiceImpl productosService;

    @Autowired
    public ProductosController(ProductosServiceImpl productosService) {
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
    public ResponseEntity<ProductosDTO> actualizar(@PathVariable Long id, @RequestBody ProductosDTO dto) {
        ProductosDTO actualizado = productosService.updateProducto(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productosService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
