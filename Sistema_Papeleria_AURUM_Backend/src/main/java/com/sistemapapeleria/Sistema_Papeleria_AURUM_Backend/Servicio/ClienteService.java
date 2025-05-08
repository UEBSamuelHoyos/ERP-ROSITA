package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ClientesDTO;
import java.util.List;

public interface ClienteService {
    ClientesDTO saveCliente(ClientesDTO clienteDTO);
    List<ClientesDTO> getAllClientes();
    ClientesDTO getClienteById(Long id);
    void deleteCliente(Long id);
}
