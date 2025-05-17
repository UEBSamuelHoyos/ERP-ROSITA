package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin(origins = "http://localhost:4200")
public class DevolucionController {

    private final DevolucionService devolucionService;

    @Autowired
    public DevolucionController(DevolucionService devolucionService) {
        this.devolucionService = devolucionService;
    }

    @PostMapping
    public ResponseEntity<DevolucionDTO> crear(@RequestBody DevolucionDTO dto) {
        return ResponseEntity.ok(devolucionService.saveDevolucion(dto));
    }

    @GetMapping
    public ResponseEntity<List<DevolucionDTO>> listarTodos() {
        return ResponseEntity.ok(devolucionService.getAllDevoluciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevolucionDTO> buscarPorId(@PathVariable Long id) {
        DevolucionDTO dto = devolucionService.getDevolucionById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        devolucionService.deleteDevolucion(id);
        return ResponseEntity.noContent().build();
    }
}
