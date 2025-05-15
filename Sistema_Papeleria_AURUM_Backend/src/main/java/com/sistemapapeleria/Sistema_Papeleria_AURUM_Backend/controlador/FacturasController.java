package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.FacturaDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.FacturaService;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "http://localhost:4200") // Cambia aquí el origen
public class FacturasController {

    private final FacturaService facturaService;

    @Autowired
    public FacturasController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> crear(@RequestBody FacturaDTO dto) {
        // Cambia esto para que devuelva un error claro si se intenta crear una factura manualmente
        throw new UnsupportedOperationException("Las facturas se generan automáticamente al registrar una venta.");
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> listarTodas() {
        return ResponseEntity.ok(facturaService.getAllFacturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> buscarPorId(@PathVariable Long id) {
        FacturaDTO dto = facturaService.getFacturaById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaService.deleteFactura(id);
        return ResponseEntity.noContent().build();
    }
}
