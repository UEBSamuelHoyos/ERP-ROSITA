package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Ventas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Cliente;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.VentaProducto;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentaProductoDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentasService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ClienteRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentaProductoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VentasServiceImpl implements VentasService {

    private static final Logger logger = LoggerFactory.getLogger(VentasServiceImpl.class);

    private final VentasRepository ventasRepository;
    private final ClienteRepository clienteRepository;
    private final ProductosRepository productosRepository;
    private final InventarioRepository inventarioRepository;
    private final VentaProductoRepository ventaProductoRepository;

    @Autowired
    public VentasServiceImpl(
        VentasRepository ventasRepository,
        ClienteRepository clienteRepository,
        ProductosRepository productosRepository,
        InventarioRepository inventarioRepository,
        VentaProductoRepository ventaProductoRepository
    ) {
        this.ventasRepository = ventasRepository;
        this.clienteRepository = clienteRepository;
        this.productosRepository = productosRepository;
        this.inventarioRepository = inventarioRepository;
        this.ventaProductoRepository = ventaProductoRepository;
    }

    @Override
    public VentasDTO saveVenta(VentasDTO dto) {
        try {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getClienteId()));

            Ventas venta = new Ventas();
            venta.setCliente(cliente);
            venta.setFecha(new java.util.Date());

            final double[] total = {0.0};
            final double[] descuento = {0.0};

            List<VentaProducto> ventaProductos = dto.getProductos().stream().map(vpDTO -> {
                Productos producto = productosRepository.findById(vpDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + vpDTO.getIdProducto()));

                if (producto.getStock() < vpDTO.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre() + " (Stock: " + producto.getStock() + ", Solicitado: " + vpDTO.getCantidad() + ")");
                }

                Inventario inventario = inventarioRepository.findByProducto_Id(producto.getId())
                    .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto: " + producto.getNombre()));

                VentaProducto vp = new VentaProducto();
                vp.setVenta(venta);
                vp.setProducto(producto);
                vp.setCantidad(vpDTO.getCantidad());
                vp.setPrecioUnitario(producto.getPrecioVenta());

                double subtotal = producto.getPrecioVenta() * vpDTO.getCantidad();
                double desc = cliente.isAfiliado() ? subtotal * 0.1 : 0.0;
                descuento[0] += desc;
                total[0] += (subtotal - desc);

                producto.reducirStock(vpDTO.getCantidad());
                productosRepository.save(producto);

                inventario.setCantidadProducto(producto.getStock());
                inventarioRepository.save(inventario);

                return vp;
            }).collect(Collectors.toList());

            venta.setProductos(ventaProductos);
            venta.setDescuento(descuento[0]);
            venta.setTotal(total[0]);

            Ventas saved = ventasRepository.save(venta);
            ventaProductoRepository.saveAll(ventaProductos);

            return mapToDTO(saved);
        } catch (RuntimeException ex) {
            logger.error("Error al guardar la venta: {}", ex.getMessage());
            throw ex;
        }
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
        dto.setClienteNombre(entity.getCliente().getNombreCompleto());
        dto.setDescuento(entity.getDescuento());
        dto.setTotal(entity.getTotal());
        dto.setFecha(entity.getFecha());
        dto.setProductos(
            entity.getProductos().stream().map(vp -> {
                VentaProductoDTO vpdto = new VentaProductoDTO();
                vpdto.setIdProducto(vp.getProducto().getId());
                vpdto.setCantidad(vp.getCantidad());
                vpdto.setPrecioUnitario(vp.getPrecioUnitario());
                return vpdto;
            }).collect(Collectors.toList())
        );
        return dto;
    }
}
