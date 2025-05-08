package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

import java.util.Date;

public class VentasDTO {
    private long id;
    private long clienteId;
    private long empleadoId;
    private long facturaId;
    private Date fecha;
    private String metodoPago;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getClienteId() {
        return clienteId;
    }
    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }
    public long getEmpleadoId() {
        return empleadoId;
    }
    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }
    public long getFacturaId() {
        return facturaId;
    }
    public void setFacturaId(long facturaId) {
        this.facturaId = facturaId;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }


    


}