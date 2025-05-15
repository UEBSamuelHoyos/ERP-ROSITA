package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentasService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:4200")
public class VentasController {

    @Autowired
    private VentasService ventasServicio;

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody VentasDTO ventaDTO) {
        try {
            VentasDTO nuevaVenta = ventasServicio.saveVenta(ventaDTO);
            return ResponseEntity.ok(nuevaVenta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<VentasDTO>> obtenerVentas() {
        return ResponseEntity.ok(ventasServicio.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id) {
        VentasDTO venta = ventasServicio.getVentaById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventasServicio.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
