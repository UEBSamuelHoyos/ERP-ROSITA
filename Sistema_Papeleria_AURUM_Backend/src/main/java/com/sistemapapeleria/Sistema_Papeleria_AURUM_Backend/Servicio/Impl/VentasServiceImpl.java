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
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Facturas;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Impuestos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentasDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.VentaProductoDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CuentasCobrarDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.VentasService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.CuentasCobrarService;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ClienteRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.VentaProductoRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.FacturasRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ImpuestosRepository;

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
    private final FacturasRepository facturasRepository;
    private final ImpuestosRepository impuestosRepository;

    @Autowired
    private CuentasCobrarService cuentasCobrarService;

    @Autowired
    public VentasServiceImpl(
        VentasRepository ventasRepository,
        ClienteRepository clienteRepository,
        ProductosRepository productosRepository,
        InventarioRepository inventarioRepository,
        VentaProductoRepository ventaProductoRepository,
        FacturasRepository facturasRepository,
        ImpuestosRepository impuestosRepository
    ) {
        this.ventasRepository = ventasRepository;
        this.clienteRepository = clienteRepository;
        this.productosRepository = productosRepository;
        this.inventarioRepository = inventarioRepository;
        this.ventaProductoRepository = ventaProductoRepository;
        this.facturasRepository = facturasRepository;
        this.impuestosRepository = impuestosRepository;
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
            final double[] subtotal = {0.0};

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

                double sub = producto.getPrecioVenta() * vpDTO.getCantidad();
                double desc = cliente.isAfiliado() ? sub * 0.1 : 0.0;
                descuento[0] += desc;
                subtotal[0] += sub;
                total[0] += (sub - desc);

                producto.reducirStock(vpDTO.getCantidad());
                productosRepository.save(producto);

                inventario.setCantidadProducto(producto.getStock());
                inventarioRepository.save(inventario);

                return vp;
            }).collect(Collectors.toList());

            // Calcular IVA sobre el subtotal antes de descuento
            double iva = subtotal[0] * 0.19;

            venta.setProductos(ventaProductos);
            venta.setDescuento(descuento[0]);
            venta.setTotal(total[0] + iva); // Aplica IVA al total de la venta

            Ventas saved = ventasRepository.save(venta);
            ventaProductoRepository.saveAll(ventaProductos);

            // === Crear la factura automáticamente ===
            Facturas factura = new Facturas();
            factura.setCliente(cliente);
            factura.setFecha(saved.getFecha());
            factura.setTotal(saved.getTotal());
            factura.setEstado("GENERADA");

            // Guardar la factura primero (sin ventas asociadas)
            Facturas savedFactura = facturasRepository.save(factura);

            // Relacionar la venta con la factura
            saved.setFactura(savedFactura);
            ventasRepository.save(saved);

            // Relacionar la factura con la venta (bidireccional, sin sobrescribir otras ventas)
            List<Ventas> ventasList = savedFactura.getVentas();
            if (ventasList == null) {
                ventasList = new java.util.ArrayList<>();
            }
            ventasList.add(saved);
            savedFactura.setVentas(ventasList);
            facturasRepository.save(savedFactura);

            // === Registrar impuesto IVA automáticamente ===
            Impuestos imp = new Impuestos();
            imp.setCantidadVentas(1);
            imp.setPorcentajeImpuesto(0.19);
            imp.setTotalImpuesto(iva);
            imp.setAnioFiscal(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
            imp.setNombreImpuesto("IVA");
            impuestosRepository.save(imp);

            // === Crear cuenta por cobrar si la venta es a crédito ===
            if (Boolean.TRUE.equals(dto.getVentaCredito())) {
                CuentasCobrarDTO cuenta = new CuentasCobrarDTO();
                cuenta.setClienteId(dto.getClienteId());
                cuenta.setMonto(dto.getTotal());
                cuenta.setEstado("PENDIENTE");
                cuentasCobrarService.saveCuentaCobrar(cuenta);
            }

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
