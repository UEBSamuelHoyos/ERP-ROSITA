package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

import java.util.List;

public class CompraProveedorRequest {
    private List<ProductoCompraDTO> productos;
    private Double montoTotal;

    public List<ProductoCompraDTO> getProductos() { return productos; }
    public void setProductos(List<ProductoCompraDTO> productos) { this.productos = productos; }

    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }
}
