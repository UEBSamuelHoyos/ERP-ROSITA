package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ImpuestosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ImpuestosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/impuestos")
@CrossOrigin(origins = "http://localhost:4200")
public class ImpuestosController {

    private final ImpuestosService impuestosService;

    @Autowired
    public ImpuestosController(ImpuestosService impuestosService) {
        this.impuestosService = impuestosService;
    }

    @PostMapping
    public ResponseEntity<ImpuestosDTO> crear(@RequestBody ImpuestosDTO dto) {
        return ResponseEntity.ok(impuestosService.saveImpuesto(dto));
    }

    @GetMapping
    public ResponseEntity<List<ImpuestosDTO>> listarTodos() {
        return ResponseEntity.ok(impuestosService.getAllImpuestos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpuestosDTO> buscarPorId(@PathVariable Long id) {
        ImpuestosDTO dto = impuestosService.getImpuestoById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        impuestosService.deleteImpuesto(id);
        return ResponseEntity.noContent().build();
    }
}
