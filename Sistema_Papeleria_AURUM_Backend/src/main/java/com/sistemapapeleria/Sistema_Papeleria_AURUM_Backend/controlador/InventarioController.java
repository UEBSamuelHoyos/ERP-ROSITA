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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.InventarioDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.InventarioService;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
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
}
