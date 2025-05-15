package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

import java.util.Date;
import java.util.List;

public class VentasDTO {
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private double descuento;
    private double total;
    private Date fecha;
    private List<VentaProductoDTO> productos;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<VentaProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<VentaProductoDTO> productos) {
        this.productos = productos;
    }
}