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
    public ClientesDTO saveCliente(ClientesDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCedula(clienteDTO.getCedula());
        cliente.setNombreCompleto(clienteDTO.getNombreCompleto());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setAfiliado(clienteDTO.isAfiliado());
        Cliente saved = clienteRepository.save(cliente);
        return mapToDTO(saved);
    }

    @Override
    public List<ClientesDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public ClientesDTO getClienteById(Long id) {
        return clienteRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClientesDTO updateCliente(Long id, ClientesDTO dto) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setCedula(dto.getCedula());
            cliente.setNombreCompleto(dto.getNombreCompleto());
            cliente.setDireccion(dto.getDireccion());
            cliente.setTelefono(dto.getTelefono());
            cliente.setAfiliado(dto.isAfiliado());
            Cliente actualizado = clienteRepository.save(cliente);
            return mapToDTO(actualizado);
        }).orElse(null);
    }

    @Override
    public ClientesDTO buscarPorCedula(String cedula) {
        Cliente cliente = clienteRepository.findByCedula(cedula);
        return cliente != null ? mapToDTO(cliente) : null;
    }

    @Override
    public List<ClientesDTO> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreCompletoContainingIgnoreCase(nombre)
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ClientesDTO mapToDTO(Cliente entity) {
        ClientesDTO dto = new ClientesDTO();
        dto.setId(entity.getId());
        dto.setCedula(entity.getCedula());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        dto.setAfiliado(entity.isAfiliado());
        return dto;
    }
}
