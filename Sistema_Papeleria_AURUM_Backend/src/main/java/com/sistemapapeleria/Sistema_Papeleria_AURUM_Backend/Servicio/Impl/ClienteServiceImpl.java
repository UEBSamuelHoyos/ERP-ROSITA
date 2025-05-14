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

        Cliente saved = clienteRepository.save(cliente);

        ClientesDTO savedDTO = new ClientesDTO();
        savedDTO.setId(saved.getId());
        savedDTO.setCedula(saved.getCedula());
        savedDTO.setNombreCompleto(saved.getNombreCompleto());
        savedDTO.setDireccion(saved.getDireccion());
        savedDTO.setTelefono(saved.getTelefono());

        return savedDTO;
    }
    
public ClientesDTO updateCliente(Long id, ClientesDTO dto) {
    return clienteRepository.findById(id).map(cliente -> {
        cliente.setCedula(dto.getCedula());
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        Cliente actualizado = clienteRepository.save(cliente);
        return mapToDTO(actualizado);
    }).orElse(null);
}


public ClientesDTO buscarPorCedula(String cedula) {
    return clienteRepository.findByCedula(cedula)
            .map(this::mapToDTO)
            .orElse(null);
}


public List<ClientesDTO> buscarPorNombre(String nombre) {
    return clienteRepository.findByNombreCompletoContainingIgnoreCase(nombre)
            .stream().map(this::mapToDTO)
            .collect(Collectors.toList());
}


private ClientesDTO mapToDTO(Cliente cliente) {
    ClientesDTO dto = new ClientesDTO();
    dto.setId(cliente.getId());
    dto.setCedula(cliente.getCedula());
    dto.setNombreCompleto(cliente.getNombreCompleto());
    dto.setDireccion(cliente.getDireccion());
    dto.setTelefono(cliente.getTelefono());
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
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public ClientesDTO getClienteById(Long id) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                ClientesDTO dto = new ClientesDTO();
                dto.setId(cliente.getId());
                dto.setCedula(cliente.getCedula());
                dto.setNombreCompleto(cliente.getNombreCompleto());
                dto.setDireccion(cliente.getDireccion());
                dto.setTelefono(cliente.getTelefono());
                return dto;
            })
            .orElse(null);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
