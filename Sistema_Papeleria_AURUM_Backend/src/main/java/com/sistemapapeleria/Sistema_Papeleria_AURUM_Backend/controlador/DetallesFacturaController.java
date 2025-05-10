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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DetallesFacturaDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DetallesFacturaService;


@RestController
@RequestMapping("/api/detalles-factura")
@CrossOrigin(origins = "*")
public class DetallesFacturaController {

    private final DetallesFacturaService detallesFacturaService;

    @Autowired
    public DetallesFacturaController(DetallesFacturaService detallesFacturaService) {
        this.detallesFacturaService = detallesFacturaService;
    }

    @PostMapping
    public ResponseEntity<DetallesFacturaDTO> crear(@RequestBody DetallesFacturaDTO detalleDTO) {
        return ResponseEntity.ok(detallesFacturaService.saveDetalleFactura(detalleDTO));
    }

    @GetMapping
    public ResponseEntity<List<DetallesFacturaDTO>> listarTodos() {
        return ResponseEntity.ok(detallesFacturaService.getAllDetallesFactura());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesFacturaDTO> buscarPorId(@PathVariable Long id) {
        DetallesFacturaDTO dto = detallesFacturaService.getDetalleFacturaById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/factura/{facturaId}")
    public ResponseEntity<List<DetallesFacturaDTO>> buscarPorFactura(@PathVariable Long facturaId) {
        return ResponseEntity.ok(detallesFacturaService.getDetallesByFacturaId(facturaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detallesFacturaService.deleteDetalleFactura(id);
        return ResponseEntity.noContent().build();
    }
}