package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

public class DevolucionesDTO {
    private long id;
    private long facturaId;
    private long productoId;
    private int cantidad;
    private String motivo;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getFacturaId() {
        return facturaId;
    }
    public void setFacturaId(long facturaId) {
        this.facturaId = facturaId;
    }
    public long getProductoId() {
        return productoId;
    }
    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


}