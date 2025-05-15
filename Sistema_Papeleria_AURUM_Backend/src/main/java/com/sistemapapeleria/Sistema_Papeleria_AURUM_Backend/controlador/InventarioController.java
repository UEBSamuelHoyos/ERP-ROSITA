package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.InventarioDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.InventarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Inventario")
@CrossOrigin(origins = "http://localhost:4200")
public class InventarioController {

    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.saveInventario(dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listarTodos() {
        return ResponseEntity.ok(inventarioService.getAllInventario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> buscarPorId(@PathVariable Long id) {
        InventarioDTO dto = inventarioService.getInventarioById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.deleteInventario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalles")
    public ResponseEntity<List<InventarioDTO>> listarConDetalles() {
        List<InventarioDTO> inventarios = inventarioService.getAllInventario();
        return ResponseEntity.ok(inventarios);
    }
}
