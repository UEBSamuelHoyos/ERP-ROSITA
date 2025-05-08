package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;
import java.util.Date;

public class FacturasDTO {
    private long id;
    private long clienteId;
    private Date fecha;
    private double total;
    private String estado;
    
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
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    


    

}