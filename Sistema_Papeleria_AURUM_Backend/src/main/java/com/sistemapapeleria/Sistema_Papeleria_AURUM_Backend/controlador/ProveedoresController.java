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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProveedoresDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProveedoresService;

@RestController
@RequestMapping("/api/Proveedores")
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
}
