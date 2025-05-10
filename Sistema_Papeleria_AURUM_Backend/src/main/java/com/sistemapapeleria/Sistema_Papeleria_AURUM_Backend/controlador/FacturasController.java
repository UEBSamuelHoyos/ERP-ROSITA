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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.FacturasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.FacturasService;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturasController {

    private final FacturasService facturasService;

    @Autowired
    public FacturasController(FacturasService facturasService) {
        this.facturasService = facturasService;
    }

    @PostMapping
    public ResponseEntity<FacturasDTO> crear(@RequestBody FacturasDTO dto) {
        return ResponseEntity.ok(facturasService.saveFactura(dto));
    }

    @GetMapping
    public ResponseEntity<List<FacturasDTO>> listarTodas() {
        return ResponseEntity.ok(facturasService.getAllFacturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturasDTO> buscarPorId(@PathVariable Long id) {
        FacturasDTO dto = facturasService.getFacturaById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturasService.deleteFactura(id);
        return ResponseEntity.noContent().build();
    }
}
