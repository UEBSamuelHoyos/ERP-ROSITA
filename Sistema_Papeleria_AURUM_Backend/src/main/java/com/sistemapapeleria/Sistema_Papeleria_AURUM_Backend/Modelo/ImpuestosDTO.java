package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

public class ImpuestosDTO {
    private long cantidadVentas; 
    private double porcentajeImpuesto; 
    private double totalImpuesto;
    private int anioFiscal;
    
    public long getCantidadVentas() {
        return cantidadVentas;
    }
    public void setCantidadVentas(long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
    public double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }
    public void setPorcentajeImpuesto(double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }
    public double getTotalImpuesto() {
        return totalImpuesto;
    }
    public void setTotalImpuesto(double totalImpuesto) {
        this.totalImpuesto = totalImpuesto;
    }
    public int getAnioFiscal() {
        return anioFiscal;
    }
    public void setAnioFiscal(int anioFiscal) {
        this.anioFiscal = anioFiscal;
    } 


}
