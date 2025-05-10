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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionesDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DevolucionesService;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin(origins = "*")
public class DevolucionesController {

    private final DevolucionesService devolucionesService;

    @Autowired
    public DevolucionesController(DevolucionesService devolucionesService) {
        this.devolucionesService = devolucionesService;
    }

    @PostMapping
    public ResponseEntity<DevolucionesDTO> crear(@RequestBody DevolucionesDTO dto) {
        return ResponseEntity.ok(devolucionesService.saveDevolucion(dto));
    }

    @GetMapping
    public ResponseEntity<List<DevolucionesDTO>> listarTodos() {
        return ResponseEntity.ok(devolucionesService.getAllDevoluciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevolucionesDTO> buscarPorId(@PathVariable Long id) {
        DevolucionesDTO dto = devolucionesService.getDevolucionById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/factura/{facturaId}")
    public ResponseEntity<List<DevolucionesDTO>> buscarPorFactura(@PathVariable Long facturaId) {
        return ResponseEntity.ok(devolucionesService.getDevolucionesByFacturaId(facturaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        devolucionesService.deleteDevolucion(id);
        return ResponseEntity.noContent().build();
    }
}
