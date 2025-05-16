package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Propiedad para el ID del producto

    private String nombre;
    private String categoria;
    private double precioCompra;
    private double precioVenta;
    private int stock;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Inventario inventario; // Relación inversa con Inventario

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedores proveedor;

    // Constructor vacío
    public Productos() {}

    // Constructor con parámetros
    public Productos(Long id, String nombre, String categoria, double precioCompra, double precioVenta, int stock, Proveedores proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public void reducirStock(int cantidad) {
        if (this.stock < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto: " + this.nombre);
        }
        this.stock -= cantidad;
    }
}
