package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

public class CuentasPagarDTO {
    private Long id;
    private Long proveedorId;
    private Double monto;
    private String estado;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
