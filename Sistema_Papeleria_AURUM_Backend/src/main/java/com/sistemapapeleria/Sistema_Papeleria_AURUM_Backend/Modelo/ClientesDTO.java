package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

public class ClientesDTO {
    private long id;
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    private boolean afiliado;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public boolean isAfiliado() {
        return afiliado;
    }
    public void setAfiliado(boolean afiliado) {
        this.afiliado = afiliado;
    }
}
