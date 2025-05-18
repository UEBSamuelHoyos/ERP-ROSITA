package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentaDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentaService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        // ...existing code para obtener cliente y productos...

        double total = calcularTotal(ventaDTO.getProductos());
        boolean descuentoAplicado = false;

        if (clienteServiceImpl.tieneDescuentoFidelizacion(ventaDTO.getClienteId())) {
            total = total * 0.95; // Aplica 5% de descuento
            descuentoAplicado = true;
        }

        ventaDTO.setTotal(total);
        ventaDTO.setDescuentoAplicado(descuentoAplicado);

        // ...guardar venta y retornar DTO...
        return ventaDTO;
    }

    private double calcularTotal(List<ProductoDTO> productos) {
        // Implementación para calcular el total de los productos
        return productos.stream()
                .mapToDouble(producto -> producto.getPrecio() * producto.getCantidad())
                .sum();
    }
}