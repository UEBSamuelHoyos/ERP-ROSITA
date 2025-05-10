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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasPagarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasPagarService;


@RestController
@RequestMapping("/api/cuentas-pagar")
@CrossOrigin(origins = "*")
public class CuentasPagarController {

    private final CuentasPagarService cuentasPagarService;

    @Autowired
    public CuentasPagarController(CuentasPagarService cuentasPagarService) {
        this.cuentasPagarService = cuentasPagarService;
    }

    @PostMapping
    public ResponseEntity<CuentasPagarDTO> crear(@RequestBody CuentasPagarDTO cuentaDTO) {
        return ResponseEntity.ok(cuentasPagarService.saveCuentaPagar(cuentaDTO));
    }

    @GetMapping
    public ResponseEntity<List<CuentasPagarDTO>> listarTodos() {
        return ResponseEntity.ok(cuentasPagarService.getAllCuentasPagar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentasPagarDTO> buscarPorId(@PathVariable Long id) {
        CuentasPagarDTO dto = cuentasPagarService.getCuentaPagarById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentasPagarService.deleteCuentaPagar(id);
        return ResponseEntity.noContent().build();
    }
}