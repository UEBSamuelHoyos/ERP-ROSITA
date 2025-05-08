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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasCobrarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasCobrarService;

@RestController
@RequestMapping("/api/cuentas-cobrar")
@CrossOrigin(origins = "*")
public class CuentasCobrarController {

    private final CuentasCobrarService cuentasCobrarService;

    @Autowired
    public CuentasCobrarController(CuentasCobrarService cuentasCobrarService) {
        this.cuentasCobrarService = cuentasCobrarService;
    }

    @PostMapping
    public ResponseEntity<CuentasCobrarDTO> crear(@RequestBody CuentasCobrarDTO cuentaDTO) {
        return ResponseEntity.ok(cuentasCobrarService.saveCuentaCobrar(cuentaDTO));
    }

    @GetMapping
    public ResponseEntity<List<CuentasCobrarDTO>> listarTodos() {
        return ResponseEntity.ok(cuentasCobrarService.getAllCuentasCobrar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentasCobrarDTO> buscarPorId(@PathVariable Long id) {
        CuentasCobrarDTO dto = cuentasCobrarService.getCuentaCobrarById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentasCobrarService.deleteCuentaCobrar(id);
        return ResponseEntity.noContent().build();
    }
}