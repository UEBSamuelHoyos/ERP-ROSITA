package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Devolucion;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Facturas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.DevolucionDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.DevolucionService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.DevolucionRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.FacturasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    private final DevolucionRepository devolucionRepository;
    private final FacturasRepository facturasRepository;
    private final ProductosRepository productosRepository;
    private final InventarioRepository inventarioRepository;

    @Autowired
    public DevolucionServiceImpl(
        DevolucionRepository devolucionRepository,
        FacturasRepository facturasRepository,
        ProductosRepository productosRepository,
        InventarioRepository inventarioRepository
    ) {
        this.devolucionRepository = devolucionRepository;
        this.facturasRepository = facturasRepository;
        this.productosRepository = productosRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public DevolucionDTO saveDevolucion(DevolucionDTO dto) {
        Facturas factura = facturasRepository.findById(dto.getFacturaId())
            .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        Productos producto = productosRepository.findById(dto.getProductoId())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Actualizar stock del producto
        producto.setStock(producto.getStock() + dto.getCantidad());
        productosRepository.save(producto);

        // Actualizar inventario
        Inventario inventario = inventarioRepository.findByProducto_Id(producto.getId())
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto"));
        inventario.setCantidadProducto(producto.getStock());
        inventarioRepository.save(inventario);

        Devolucion devolucion = new Devolucion();
        devolucion.setFactura(factura);
        devolucion.setProducto(producto);
        devolucion.setCantidad(dto.getCantidad());
        devolucion.setFechaDevolucion(new Date());

        Devolucion saved = devolucionRepository.save(devolucion);
        return mapToDTO(saved);
    }

    @Override
    public List<DevolucionDTO> getAllDevoluciones() {
        return devolucionRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public DevolucionDTO getDevolucionById(Long id) {
        return devolucionRepository.findById(id)
            .map(this::mapToDTO)
            .orElse(null);
    }

    @Override
    public void deleteDevolucion(Long id) {
        devolucionRepository.deleteById(id);
    }

    private DevolucionDTO mapToDTO(Devolucion entity) {
        DevolucionDTO dto = new DevolucionDTO();
        dto.setId(entity.getId());
        dto.setFacturaId(entity.getFactura().getId());
        dto.setProductoId(entity.getProducto().getId());
        dto.setCantidad(entity.getCantidad());
        dto.setFechaDevolucion(entity.getFechaDevolucion());
        return dto;
    }
}
