package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProveedoresDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProveedoresService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CompraProveedorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    @Autowired
    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @PostMapping
    public ResponseEntity<ProveedoresDTO> crear(@RequestBody ProveedoresDTO dto) {
        return ResponseEntity.ok(proveedoresService.saveProveedor(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProveedoresDTO>> listarTodos() {
        return ResponseEntity.ok(proveedoresService.getAllProveedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedoresDTO> buscarPorId(@PathVariable Long id) {
        ProveedoresDTO dto = proveedoresService.getProveedorById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedoresService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para registrar compra a proveedor y actualizar inventario
    @PostMapping("/{proveedorId}/comprar")
    public ResponseEntity<String> comprarProductos(
            @PathVariable Long proveedorId,
            @RequestBody CompraProveedorRequest request) {
        proveedoresService.comprarProductos(proveedorId, request);
        return ResponseEntity.ok("Compra registrada y stock actualizado");
    }
}
