package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Empleados;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.RegistroAsistencia;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.EmpleadosDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.EmpleadosService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.EmpleadosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.RegistroAsistenciaRepository;

@Service
public class EmpleadosServiceImpl implements EmpleadosService {

    private final EmpleadosRepository empleadosRepository;
    private final RegistroAsistenciaRepository asistenciaRepository;

    @Autowired
    public EmpleadosServiceImpl(EmpleadosRepository empleadosRepository, RegistroAsistenciaRepository asistenciaRepository) {
        this.empleadosRepository = empleadosRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public EmpleadosDTO saveEmpleado(EmpleadosDTO dto) {
        Empleados emp = new Empleados();
        emp.setCedula(dto.getCedula());
        emp.setNombreCompleto(dto.getNombreCompleto());
        emp.setCargo(dto.getCargo());
        emp.setTelefono(dto.getTelefono());

        Empleados saved = empleadosRepository.saveAndFlush(emp); // Usa saveAndFlush para asegurar el ID

        // Registrar asistencia automáticamente SOLO si el empleado se creó correctamente y tiene ID
        if (saved.getId() != null) {
            RegistroAsistencia asistencia = new RegistroAsistencia();
            asistencia.setEmpleado(saved);
            asistencia.setFechaHora(new Date());
            asistenciaRepository.saveAndFlush(asistencia);
        }

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

    @Override
    public void registrarAsistencia(Long empleadoId) {
        Empleados empleado = empleadosRepository.findById(empleadoId)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        RegistroAsistencia asistencia = new RegistroAsistencia();
        asistencia.setEmpleado(empleado);
        asistencia.setFechaHora(new Date());
        // Asegúrate de guardar la asistencia y hacer flush para que se escriba en la base de datos
        asistenciaRepository.saveAndFlush(asistencia);
    }

    @Override
    public List<RegistroAsistencia> getAsistencias(Long empleadoId) {
        // Verifica que realmente existan asistencias en la tabla y que el empleadoId sea correcto
        return asistenciaRepository.findByEmpleado_Id(empleadoId);
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
