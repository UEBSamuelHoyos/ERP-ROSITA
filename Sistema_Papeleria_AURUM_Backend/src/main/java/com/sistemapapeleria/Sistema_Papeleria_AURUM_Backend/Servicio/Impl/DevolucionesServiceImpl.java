package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Devoluciones;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Facturas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionesDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DevolucionesService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.DevolucionesRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.FacturasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;

@Service
public class DevolucionesServiceImpl implements DevolucionesService {

    private final DevolucionesRepository devolucionesRepository;
    private final FacturasRepository facturasRepository;
    private final ProductosRepository productosRepository;
    private final InventarioRepository inventarioRepository;

    @Autowired
    public DevolucionesServiceImpl(
        DevolucionesRepository devolucionesRepository,
        FacturasRepository facturasRepository,
        ProductosRepository productosRepository,
        InventarioRepository inventarioRepository
    ) {
        this.devolucionesRepository = devolucionesRepository;
        this.facturasRepository = facturasRepository;
        this.productosRepository = productosRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public DevolucionesDTO saveDevolucion(DevolucionesDTO dto) {
        Devoluciones entity = new Devoluciones();
        Facturas factura = facturasRepository.findById(dto.getFacturaId()).orElse(null);
        Productos producto = productosRepository.findById(dto.getProductoId()).orElse(null);
        entity.setFactura(factura);
        entity.setProducto(producto);
        entity.setCantidad(dto.getCantidad());
        entity.setMotivo(dto.getMotivo());

        // Validar que no se devuelvan más productos de los vendidos en la factura
        int cantidadVendida = factura.getVentas().stream()
            .flatMap(venta -> venta.getProductos().stream())
            .filter(vp -> vp.getProducto().getId().equals(producto.getId()))
            .mapToInt(vp -> vp.getCantidad())
            .sum();

        int cantidadDevuelta = devolucionesRepository.findAll().stream()
            .filter(d -> d.getFactura() != null && d.getFactura().getId().equals(factura.getId()))
            .filter(d -> d.getProducto() != null && d.getProducto().getId().equals(producto.getId()))
            .mapToInt(d -> d.getCantidad())
            .sum();

        if (dto.getCantidad() + cantidadDevuelta > cantidadVendida) {
            throw new RuntimeException("No se puede devolver más productos de los vendidos en la factura.");
        }

        // Actualizar el stock del producto
        if (producto != null) {
            producto.setStock(producto.getStock() + dto.getCantidad());
            productosRepository.save(producto);

            // Actualizar el stock del inventario relacionado
            inventarioRepository.findByProducto_Id(producto.getId()).ifPresent(inventario -> {
                inventario.setCantidadProducto(producto.getStock());
                inventarioRepository.save(inventario);
            });
        }

        Devoluciones saved = devolucionesRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<DevolucionesDTO> getAllDevoluciones() {
        return devolucionesRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public DevolucionesDTO getDevolucionById(Long id) {
        return devolucionesRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public List<DevolucionesDTO> getDevolucionesByFacturaId(Long facturaId) {
        return devolucionesRepository.findAll().stream()
            .filter(d -> d.getFactura() != null && d.getFactura().getId().equals(facturaId))
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteDevolucion(Long id) {
        devolucionesRepository.deleteById(id);
    }

    private DevolucionesDTO mapToDTO(Devoluciones entity) {
        DevolucionesDTO dto = new DevolucionesDTO();
        dto.setId(entity.getId());
        dto.setFacturaId(entity.getFactura() != null ? entity.getFactura().getId() : null);
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getId() : null);
        dto.setCantidad(entity.getCantidad());
        dto.setMotivo(entity.getMotivo());
        return dto;
    }
}
