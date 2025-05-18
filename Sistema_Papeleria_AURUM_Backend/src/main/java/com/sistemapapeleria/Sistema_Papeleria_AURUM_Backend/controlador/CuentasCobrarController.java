package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasCobrarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl.CuentasCobrarServiceImpl;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl.CuentasPagarServiceImpl;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CuentasCobrarController {

    private final CuentasCobrarServiceImpl cuentasCobrarService;
    private final CuentasPagarServiceImpl cuentasPagarService;

    @Autowired
    public CuentasCobrarController(CuentasCobrarServiceImpl cuentasCobrarService, CuentasPagarServiceImpl cuentasPagarService) {
        this.cuentasCobrarService = cuentasCobrarService;
        this.cuentasPagarService = cuentasPagarService;
    }

    // 1. Crear nueva cuenta
    @PostMapping
    public ResponseEntity<CuentasCobrarDTO> crear(@RequestBody CuentasCobrarDTO dto) {
        CuentasCobrarDTO creada = cuentasCobrarService.saveCuentaCobrar(dto);
        return ResponseEntity.ok(creada);
    }

    // 2. Obtener todas las cuentas (por cobrar y por pagar)
    @GetMapping
    public ResponseEntity<List<CuentasCobrarDTO>> obtenerTodas() {
        List<CuentasCobrarDTO> lista = cuentasCobrarService.getAllCuentasCobrar();
        return ResponseEntity.ok(lista);
    }

    // 3. Obtener una cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<CuentasCobrarDTO> obtenerPorId(@PathVariable Long id) {
        CuentasCobrarDTO cuenta = cuentasCobrarService.getCuentaCobrarById(id);
        return cuenta != null ? ResponseEntity.ok(cuenta) : ResponseEntity.notFound().build();
    }

    // 4. Eliminar cuenta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentasCobrarService.deleteCuentaCobrar(id);
        return ResponseEntity.noContent().build();
    }

    // 5. Actualizar cuenta por ID
    @PutMapping("/{id}")
    public ResponseEntity<CuentasCobrarDTO> actualizar(@PathVariable Long id, @RequestBody CuentasCobrarDTO dto) {
        CuentasCobrarDTO actualizada = cuentasCobrarService.updateCuentaCobrar(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    // 6. Buscar por cliente ID
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CuentasCobrarDTO>> obtenerPorCliente(@PathVariable Long clienteId) {
        List<CuentasCobrarDTO> cuentas = cuentasCobrarService.getCuentasByClienteId(clienteId);
        return ResponseEntity.ok(cuentas);
    }

    // 7. Marcar cuenta como pagada
    @PutMapping("/{id}/pagar")
    public ResponseEntity<CuentasCobrarDTO> marcarComoPagada(@PathVariable Long id) {
        CuentasCobrarDTO actualizada = cuentasCobrarService.marcarComoPagada(id);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }
}
