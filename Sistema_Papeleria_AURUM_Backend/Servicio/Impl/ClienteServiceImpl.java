package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Cliente;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ClientesDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ClienteService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClientesDTO saveCliente(ClientesDTO ClientesDTO) {
        Cliente cliente = new Cliente();
        cliente.setCedula(ClientesDTO.getCedula());
        cliente.setNombreCompleto(ClientesDTO.getNombreCompleto());
        cliente.setDireccion(ClientesDTO.getDireccion());
        cliente.setTelefono(ClientesDTO.getTelefono());
        cliente.setAfiliado(ClientesDTO.isAfiliado()); // Nuevo campo

        Cliente saved = clienteRepository.save(cliente);

        ClientesDTO savedDTO = new ClientesDTO();
        savedDTO.setId(saved.getId());
        savedDTO.setCedula(saved.getCedula());
        savedDTO.setNombreCompleto(saved.getNombreCompleto());
        savedDTO.setDireccion(saved.getDireccion());
        savedDTO.setTelefono(saved.getTelefono());
        savedDTO.setAfiliado(saved.isAfiliado()); // Nuevo campo

        return savedDTO;
    }

    @Override
    public ClientesDTO updateCliente(Long id, ClientesDTO dto) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setCedula(dto.getCedula());
            cliente.setNombreCompleto(dto.getNombreCompleto());
            cliente.setDireccion(dto.getDireccion());
            cliente.setTelefono(dto.getTelefono());
            cliente.setAfiliado(dto.isAfiliado()); // Nuevo campo
            Cliente actualizado = clienteRepository.save(cliente);
            return mapToDTO(actualizado);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));
    }

    @Override
    public ClientesDTO buscarPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con la cédula: " + cedula));
    }

    public List<ClientesDTO> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreCompletoContainingIgnoreCase(nombre)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ClientesDTO mapToDTO(Cliente cliente) {
        ClientesDTO dto = new ClientesDTO();
        dto.setId(cliente.getId());
        dto.setCedula(cliente.getCedula());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setAfiliado(cliente.isAfiliado()); // Nuevo campo
        return dto;
    }

    @Override
    public List<ClientesDTO> getAllClientes() {
        return clienteRepository.findAll()
            .stream()
            .map(cliente -> {
                ClientesDTO dto = new ClientesDTO();
                dto.setId(cliente.getId());
                dto.setCedula(cliente.getCedula());
                dto.setNombreCompleto(cliente.getNombreCompleto());
                dto.setDireccion(cliente.getDireccion());
                dto.setTelefono(cliente.getTelefono());
                dto.setAfiliado(cliente.isAfiliado()); // Nuevo campo
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public ClientesDTO getClienteById(Long id) {
        return clienteRepository.findById(id)
            .map(this::mapToDTO) // Map the entity to DTO
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el ID: " + id));
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Método auxiliar para saber si un cliente tiene descuento de fidelización
    public boolean tieneDescuentoFidelizacion(Long clienteId) {
        return clienteRepository.findById(clienteId)
            .map(Cliente::isAfiliado)
            .orElse(false);
    }
}