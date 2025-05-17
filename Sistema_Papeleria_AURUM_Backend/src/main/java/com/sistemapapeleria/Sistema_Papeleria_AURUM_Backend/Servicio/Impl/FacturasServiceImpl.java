package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Facturas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Cliente;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.FacturaDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.FacturaService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.FacturasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ClienteRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;

@Service
public class FacturasServiceImpl implements FacturaService {

    private static final Logger logger = LoggerFactory.getLogger(FacturasServiceImpl.class);

    private final FacturasRepository facturasRepository;
    private final ClienteRepository clienteRepository;
    private final VentasRepository ventasRepository;
    private final ProductosRepository productosRepository;

    @Autowired
    public FacturasServiceImpl(FacturasRepository facturasRepository, ClienteRepository clienteRepository, VentasRepository ventasRepository, ProductosRepository productosRepository) {
        this.facturasRepository = facturasRepository;
        this.clienteRepository = clienteRepository;
        this.ventasRepository = ventasRepository;
        this.productosRepository = productosRepository;
    }

    @Override
    public FacturaDTO saveFactura(FacturaDTO dto) {
        throw new UnsupportedOperationException("Las facturas se generan automáticamente al registrar una venta.");
    }

    @Override
    public List<FacturaDTO> getAllFacturas() {
        return facturasRepository.findAll().stream()
                .map(f -> {
                    List<Ventas> ventasList = f.getVentas();
                    if (ventasList == null) {
                        logger.warn("Factura con id {} no tiene ventas asociadas (lista null)", f.getId());
                        ventasList = List.of();
                    }
                    return mapToDTO(f, f.getCliente(), ventasList);
                })
                .collect(Collectors.toList());
    }

    @Override
    public FacturaDTO getFacturaById(Long id) {
        return facturasRepository.findById(id)
                .map(f -> {
                    List<Ventas> ventasList = f.getVentas();
                    if (ventasList == null) {
                        logger.warn("Factura con id {} no tiene ventas asociadas (lista null)", f.getId());
                        ventasList = List.of();
                    }
                    return mapToDTO(f, f.getCliente(), ventasList);
                })
                .orElse(null);
    }

    @Override
    public void deleteFactura(Long id) {
        facturasRepository.deleteById(id);
    }

    /**
     * Procesa la devolución de una venta y actualiza el stock de los productos.
     */
    public void procesarDevolucion(Long ventaId) {
        Ventas venta = ventasRepository.findById(ventaId).orElse(null);
        if (venta == null) {
            logger.warn("No se encontró la venta con id {}", ventaId);
            return;
        }
        // Suponiendo que la entidad Ventas tiene una lista de productos y cantidades
        venta.getProductos().forEach(ventaProducto -> {
            Productos producto = ventaProducto.getProducto();
            int cantidadDevuelta = ventaProducto.getCantidad();
            producto.setStock(producto.getStock() + cantidadDevuelta);
            productosRepository.save(producto);
        });
        // Aquí puedes marcar la venta como devuelta si lo necesitas
        logger.info("Stock actualizado por devolución de venta {}", ventaId);
    }

    private FacturaDTO mapToDTO(Facturas entity, Cliente cliente, List<Ventas> ventas) {
        FacturaDTO dto = new FacturaDTO();
        dto.setId(entity.getId());
        dto.setClienteId(cliente != null ? cliente.getId() : null);
        dto.setFecha(entity.getFecha());
        dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());
        dto.setVentas(
            ventas.stream().map(v -> {
                VentasDTO vdto = new VentasDTO();
                vdto.setId(v.getId());
                vdto.setClienteId(v.getCliente().getId());
                vdto.setClienteNombre(v.getCliente().getNombreCompleto());
                vdto.setDescuento(v.getDescuento());
                vdto.setTotal(v.getTotal());
                vdto.setFecha(v.getFecha());
                // Puedes agregar productos si lo necesitas
                return vdto;
            }).collect(Collectors.toList())
        );
        return dto;
    }
}
