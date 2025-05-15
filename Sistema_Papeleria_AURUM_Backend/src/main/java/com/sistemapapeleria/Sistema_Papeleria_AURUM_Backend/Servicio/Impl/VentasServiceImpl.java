package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Cliente;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentasService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ClienteRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;

@Service
public class VentasServiceImpl implements VentasService {

    private final VentasRepository ventasRepository;
    private final ClienteRepository clienteRepository;
    private final ProductosRepository productosRepository;

    @Autowired
    public VentasServiceImpl(VentasRepository ventasRepository, ClienteRepository clienteRepository, ProductosRepository productosRepository) {
        this.ventasRepository = ventasRepository;
        this.clienteRepository = clienteRepository;
        this.productosRepository = productosRepository;
    }

    @Override
    public VentasDTO saveVenta(VentasDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Productos producto = productosRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para el producto");
        }

        Ventas venta = new Ventas();
        venta.setCliente(cliente);
        venta.setProducto(producto);
        venta.setCantidad(dto.getCantidad());
        venta.setPrecioUnitario(producto.getPrecioVenta());
        venta.setDescuento(cliente.isAfiliado() ? producto.getPrecioVenta() * dto.getCantidad() * 0.1 : 0);
        venta.setTotal((producto.getPrecioVenta() * dto.getCantidad()) - venta.getDescuento());
        venta.setFecha(new java.util.Date());

        // Reducir el stock del producto
        producto.reducirStock(dto.getCantidad());
        productosRepository.save(producto);

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
        dto.setClienteId(entity.getCliente().getId());
        dto.setProductoId(entity.getProducto().getId());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setDescuento(entity.getDescuento());
        dto.setTotal(entity.getTotal());
        return dto;
    }
}
