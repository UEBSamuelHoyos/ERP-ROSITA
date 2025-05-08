package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import java.util.List;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ClientesDTO;

public interface ClienteService {
    ClientesDTO saveCliente(ClientesDTO clienteDTO);
    List<ClientesDTO> getAllClientes();
    ClientesDTO getClienteById(Long id);
    void deleteCliente(Long id);
}
