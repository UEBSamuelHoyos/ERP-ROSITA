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

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ClientesDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")  // Permite peticiones desde cualquier origen (útil para conectar con Angular)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClientesDTO> guardarCliente(@RequestBody ClientesDTO clienteDTO) {
        ClientesDTO guardado = clienteService.saveCliente(clienteDTO);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public ResponseEntity<List<ClientesDTO>> obtenerTodos() {
        List<ClientesDTO> lista = clienteService.getAllClientes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesDTO> obtenerPorId(@PathVariable Long id) {
        ClientesDTO cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
