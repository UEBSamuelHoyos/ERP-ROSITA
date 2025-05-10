package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Empleados;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.EmpleadosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.EmpleadosService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.EmpleadosRepository;

@Service
public class EmpleadosServiceImpl implements EmpleadosService {

    private final EmpleadosRepository empleadosRepository;

    @Autowired
    public EmpleadosServiceImpl(EmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    @Override
    public EmpleadosDTO saveEmpleado(EmpleadosDTO dto) {
        Empleados emp = new Empleados();
        emp.setCedula(dto.getCedula());
        emp.setNombreCompleto(dto.getNombreCompleto());
        emp.setCargo(dto.getCargo());
        emp.setTelefono(dto.getTelefono());

        Empleados saved = empleadosRepository.save(emp);
        return mapToDTO(saved);
    }

    @Override
    public List<EmpleadosDTO> getAllEmpleados() {
        return empleadosRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public EmpleadosDTO getEmpleadoById(Long id) {
        return empleadosRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public void deleteEmpleado(Long id) {
        empleadosRepository.deleteById(id);
    }

    private EmpleadosDTO mapToDTO(Empleados emp) {
        EmpleadosDTO dto = new EmpleadosDTO();
        dto.setId(emp.getId());
        dto.setCedula(emp.getCedula());
        dto.setNombreCompleto(emp.getNombreCompleto());
        dto.setCargo(emp.getCargo());
        dto.setTelefono(emp.getTelefono());
        return dto;
    }
}
