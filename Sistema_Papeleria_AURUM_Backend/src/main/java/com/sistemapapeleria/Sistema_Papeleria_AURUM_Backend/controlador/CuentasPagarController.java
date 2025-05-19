package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasPagarService;

@RestController
@RequestMapping("/api/cuentas-pagar")
<<<<<<< Updated upstream
@CrossOrigin(origins = "*")
=======
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
>>>>>>> Stashed changes
public class CuentasPagarController {

    private final CuentasPagarService cuentasPagarService;

    @Autowired
    public CuentasPagarController(CuentasPagarService cuentasPagarService) {
        this.cuentasPagarService = cuentasPagarService;
    }

    // 1. Crear nueva cuenta por pagar
    @PostMapping
    public ResponseEntity<CuentasPagarDTO> crear(@RequestBody CuentasPagarDTO dto) {
        CuentasPagarDTO creada = cuentasPagarService.saveCuentaPagar(dto);
        return ResponseEntity.ok(creada);
    }

    // 2. Obtener todas las cuentas por pagar
    @GetMapping
    public ResponseEntity<List<CuentasPagarDTO>> obtenerTodas() {
        return ResponseEntity.ok(cuentasPagarService.getAllCuentasPagar());
    }

    // 3. Obtener cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<CuentasPagarDTO> obtenerPorId(@PathVariable Long id) {
        CuentasPagarDTO cuenta = cuentasPagarService.getCuentaPagarById(id);
        return cuenta != null ? ResponseEntity.ok(cuenta) : ResponseEntity.notFound().build();
    }

    // 4. Eliminar cuenta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentasPagarService.deleteCuentaPagar(id);
        return ResponseEntity.noContent().build();
    }
<<<<<<< Updated upstream
}
=======

    // 5. Actualizar cuenta por ID
    @PutMapping("/{id}")
    public ResponseEntity<CuentasPagarDTO> actualizar(@PathVariable Long id, @RequestBody CuentasPagarDTO dto) {
        CuentasPagarDTO actualizada = cuentasPagarService.updateCuentaPagar(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    // 6. Buscar por proveedor ID
    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<CuentasPagarDTO>> obtenerPorProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(cuentasPagarService.getCuentasByProveedorId(proveedorId));
    }

    // 7. Marcar cuenta como pagada
    @PutMapping("/{id}/pagar")
    public ResponseEntity<CuentasPagarDTO> marcarComoPagada(@PathVariable Long id) {
        CuentasPagarDTO actualizada = cuentasPagarService.marcarComoPagada(id);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }
}
>>>>>>> Stashed changes
