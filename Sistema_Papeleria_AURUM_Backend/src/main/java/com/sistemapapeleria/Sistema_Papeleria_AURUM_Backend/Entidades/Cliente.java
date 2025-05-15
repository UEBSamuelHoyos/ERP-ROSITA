package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Long id;
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    private boolean afiliado;
    
    public Cliente(){

    }
    public Cliente(Long id, String nombreCompleto, String direccion, String telefono){
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

