package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProductosService;

@RestController
@RequestMapping("/api/Productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductosController {

    private final ProductosService productosService;

    @Autowired
    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @PostMapping
    public ResponseEntity<ProductosDTO> crear(@RequestBody ProductosDTO dto) {
        return ResponseEntity.ok(productosService.saveProducto(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductosDTO>> listarTodos() {
        return ResponseEntity.ok(productosService.getAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> buscarPorId(@PathVariable Long id) {
        ProductosDTO dto = productosService.getProductoById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productosService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
