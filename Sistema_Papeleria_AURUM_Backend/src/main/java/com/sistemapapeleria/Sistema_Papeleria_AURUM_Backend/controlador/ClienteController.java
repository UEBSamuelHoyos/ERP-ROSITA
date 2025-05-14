package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ClientesDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ClienteService;

@RestController
@RequestMapping("/api/Cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Crear cliente
    @PostMapping
    public ResponseEntity<ClientesDTO> guardarCliente(@RequestBody ClientesDTO clienteDTO) {
        ClientesDTO guardado = clienteService.saveCliente(clienteDTO);
        return ResponseEntity.ok(guardado);
    }

    // Listar todos los clientes
    @GetMapping
    public ResponseEntity<List<ClientesDTO>> obtenerTodos() {
        List<ClientesDTO> lista = clienteService.getAllClientes();
        return ResponseEntity.ok(lista);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientesDTO> obtenerPorId(@PathVariable Long id) {
        ClientesDTO cliente = clienteService.getClienteById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<ClientesDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClientesDTO clienteDTO) {
        ClientesDTO actualizado = clienteService.updateCliente(id, clienteDTO);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

  
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<ClientesDTO> buscarPorCedula(@PathVariable String cedula) {
        ClientesDTO cliente = clienteService.buscarPorCedula(cedula);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    
    @GetMapping("/buscar")
    public ResponseEntity<List<ClientesDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<ClientesDTO> clientes = clienteService.buscarPorNombre(nombre);
        return ResponseEntity.ok(clientes);
    }
}
