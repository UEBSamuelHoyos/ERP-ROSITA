package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "venta_producto")
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Ventas venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    private int cantidad;
    private double precioUnitario;

    // ...getters y setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Ventas getVenta() { return venta; }
    public void setVenta(Ventas venta) { this.venta = venta; }

    public Productos getProducto() { return producto; }
    public void setProducto(Productos producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
}
