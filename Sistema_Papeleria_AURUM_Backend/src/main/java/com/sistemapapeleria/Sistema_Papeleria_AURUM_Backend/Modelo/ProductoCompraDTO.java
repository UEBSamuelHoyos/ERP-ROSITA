package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

public class ProductoCompraDTO {
    private Long productoId;
    private int cantidad;
    private double precioUnitario;
    private Long proveedorId; // Nuevo campo
    private String nombre; // <-- Agrega este campo

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
