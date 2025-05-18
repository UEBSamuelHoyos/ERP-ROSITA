package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.Impl;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CompraProveedor;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Proveedores;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Productos;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Inventario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.CuentasPagar;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CompraProveedorRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProveedoresRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.ProductosRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.InventarioRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.CuentasPagarRepository;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.CompraProveedorRequest;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProductoCompraDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo.ProveedoresDTO;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Servicio.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;
    private final ProductosRepository productosRepository;
    private final InventarioRepository inventarioRepository;
    private final CompraProveedorRepository compraProveedorRepository;
    private final CuentasPagarRepository cuentasPagarRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProveedoresServiceImpl.class);

    @Autowired
    public ProveedoresServiceImpl(
        ProveedoresRepository proveedoresRepository,
        ProductosRepository productosRepository,
        InventarioRepository inventarioRepository,
        CompraProveedorRepository compraProveedorRepository,
        CuentasPagarRepository cuentasPagarRepository
    ) {
        this.proveedoresRepository = proveedoresRepository;
        this.productosRepository = productosRepository;
        this.inventarioRepository = inventarioRepository;
        this.compraProveedorRepository = compraProveedorRepository;
        this.cuentasPagarRepository = cuentasPagarRepository;
    }

    @Override
    public ProveedoresDTO saveProveedor(ProveedoresDTO dto) {
        Proveedores entity = new Proveedores();
        entity.setNombre(dto.getNombre());
        entity.setContacto(dto.getContacto());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        Proveedores saved = proveedoresRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<ProveedoresDTO> getAllProveedores() {
        return proveedoresRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedoresDTO getProveedorById(Long id) {
        return proveedoresRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public void deleteProveedor(Long id) {
        proveedoresRepository.deleteById(id);
    }

    @Override
    public void comprarProductos(Long proveedorId, CompraProveedorRequest request) {
        if (proveedorId == null || proveedorId == 0) {
            throw new IllegalArgumentException("El ID del proveedor no puede ser 0 o null.");
        }
        Proveedores proveedor = proveedoresRepository.findById(proveedorId)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + proveedorId));

        double montoTotal = 0.0;

        for (ProductoCompraDTO pc : request.getProductos()) {
            Productos producto = null;
            if (pc.getProductoId() != null) {
                producto = productosRepository.findById(pc.getProductoId()).orElse(null);
            } else if (pc.getNombre() != null && !pc.getNombre().isEmpty()) {
                producto = productosRepository.findAll().stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(pc.getNombre()) && 
                                 p.getProveedor() != null && 
                                 p.getProveedor().getId().equals(proveedorId))
                    .findFirst()
                    .orElse(null);
            }
            if (producto == null) {
                if (pc.getNombre() == null || pc.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del producto no puede ser vacío.");
                }
                if (pc.getPrecioUnitario() <= 0) {
                    throw new IllegalArgumentException("El precio unitario debe ser mayor a 0.");
                }
                producto = new Productos();
                producto.setNombre(pc.getNombre());
                producto.setProveedor(proveedor);
                producto.setStock(pc.getCantidad());
                producto.setPrecioCompra(pc.getPrecioUnitario());
                producto.setPrecioVenta(pc.getPrecioUnitario() * 1.2);
                producto.setCategoria("Papelería");
                producto = productosRepository.save(producto);

                Inventario inventario = new Inventario();
                inventario.setProducto(producto);
                inventario.setCantidadProducto(pc.getCantidad());
                inventario.setNombreProducto(producto.getNombre());
                inventario.setCategoria(producto.getCategoria());
                inventario.setFechaIngreso(new java.util.Date());
                inventarioRepository.save(inventario);
            } else {
                producto.setStock(producto.getStock() + pc.getCantidad());
                productosRepository.save(producto);

                Inventario inventario = inventarioRepository.findByProducto_Id(producto.getId()).orElse(null);
                if (inventario == null) {
                    inventario = new Inventario();
                    inventario.setProducto(producto);
                    inventario.setNombreProducto(producto.getNombre());
                    inventario.setCategoria(producto.getCategoria());
                    inventario.setFechaIngreso(new java.util.Date());
                }
                inventario.setCantidadProducto(producto.getStock());
                inventarioRepository.save(inventario);
            }

            // Registrar la compra en la tabla compras_proveedor
            CompraProveedor compra = new CompraProveedor();
            compra.setProveedor(proveedor);
            compra.setProducto(producto);
            compra.setCantidad(pc.getCantidad());
            compra.setPrecioUnitario(pc.getPrecioUnitario());
            compra.setFechaCompra(new java.util.Date());
            compraProveedorRepository.save(compra);

            montoTotal += pc.getCantidad() * pc.getPrecioUnitario();
        }

        // Registrar el egreso como cuenta por pagar
        if (montoTotal > 0) {
            CuentasPagar cuenta = new CuentasPagar();
            cuenta.setProveedorId(proveedorId);
            cuenta.setMonto(montoTotal);
            cuenta.setEstado("PENDIENTE");
            cuentasPagarRepository.save(cuenta);
            logger.info("Cuenta por pagar registrada: proveedorId={}, monto={}", proveedorId, montoTotal);
        }
    }

    private ProveedoresDTO mapToDTO(Proveedores entity) {
        ProveedoresDTO dto = new ProveedoresDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setContacto(entity.getContacto());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        return dto;
    }
}