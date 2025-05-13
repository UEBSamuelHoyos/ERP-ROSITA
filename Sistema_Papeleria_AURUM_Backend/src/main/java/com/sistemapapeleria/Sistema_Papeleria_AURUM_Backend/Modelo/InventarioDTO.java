package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

import java.util.Date;

public class InventarioDTO {
    private String categoria;
    private Date fechaIngreso;
    private String nombreProducto;
    private long idProducto;
    private int cantidadProducto;
    
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
    public int getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }


}
