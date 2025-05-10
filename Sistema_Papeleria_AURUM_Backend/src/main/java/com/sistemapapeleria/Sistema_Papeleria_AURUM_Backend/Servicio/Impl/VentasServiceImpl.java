package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentasService;

@Service
public class VentasServiceImpl implements VentasService {

    private final VentasRepository ventasRepository;

    @Autowired
    public VentasServiceImpl(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public VentasDTO saveVenta(VentasDTO dto) {
        Ventas venta = new Ventas();
        venta.setClienteId(dto.getClienteId());
        venta.setEmpleadoId(dto.getEmpleadoId());
        venta.setFacturaId(dto.getFacturaId());
        venta.setFecha(dto.getFecha());
        venta.setMetodoPago(dto.getMetodoPago());

        Ventas saved = ventasRepository.save(venta);
        return mapToDTO(saved);
    }

    @Override
    public List<VentasDTO> getAllVentas() {
        return ventasRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentasDTO getVentaById(Long id) {
        return ventasRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteVenta(Long id) {
        ventasRepository.deleteById(id);
    }

    private VentasDTO mapToDTO(Ventas entity) {
        VentasDTO dto = new VentasDTO();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getClienteId());
        dto.setEmpleadoId(entity.getEmpleadoId());
        dto.setFacturaId(entity.getFacturaId());
        dto.setFecha(entity.getFecha());
        dto.setMetodoPago(entity.getMetodoPago());
        return dto;
    }
}
