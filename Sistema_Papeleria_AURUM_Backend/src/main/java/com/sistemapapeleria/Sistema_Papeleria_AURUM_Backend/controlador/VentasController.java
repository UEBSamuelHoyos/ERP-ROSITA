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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentasService;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentasController {

    private final VentasService ventasService;

    @Autowired
    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @PostMapping
    public ResponseEntity<VentasDTO> crear(@RequestBody VentasDTO dto) {
        return ResponseEntity.ok(ventasService.saveVenta(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentasDTO>> listarTodos() {
        return ResponseEntity.ok(ventasService.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentasDTO> buscarPorId(@PathVariable Long id) {
        VentasDTO dto = ventasService.getVentaById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventasService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
