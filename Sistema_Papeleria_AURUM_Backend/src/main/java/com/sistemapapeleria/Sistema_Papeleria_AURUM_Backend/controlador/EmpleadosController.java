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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.EmpleadosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.EmpleadosService;

@RestController
@RequestMapping("/api/Empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadosController {

    private final EmpleadosService empleadosService;

    @Autowired
    public EmpleadosController(EmpleadosService empleadosService) {
        this.empleadosService = empleadosService;
    }

    @PostMapping
    public ResponseEntity<EmpleadosDTO> crear(@RequestBody EmpleadosDTO dto) {
        return ResponseEntity.ok(empleadosService.saveEmpleado(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadosDTO>> listar() {
        return ResponseEntity.ok(empleadosService.getAllEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadosDTO> buscar(@PathVariable Long id) {
        EmpleadosDTO dto = empleadosService.getEmpleadoById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadosService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
