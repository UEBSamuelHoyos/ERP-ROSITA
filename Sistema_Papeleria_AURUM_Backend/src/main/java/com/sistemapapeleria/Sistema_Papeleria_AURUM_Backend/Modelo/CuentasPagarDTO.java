package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;
public class CuentasPagarDTO {
    private long id;
    private long proveedorId;
    private double monto;
    private String estado;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getProveedorId() {
        return proveedorId;
    }
    public void setProveedorId(long proveedorId) {
        this.proveedorId = proveedorId;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}