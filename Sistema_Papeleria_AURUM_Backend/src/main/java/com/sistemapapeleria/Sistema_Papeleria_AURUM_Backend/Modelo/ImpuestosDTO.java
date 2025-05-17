package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;

public class ImpuestosDTO {
    private Long id;
    private Integer cantidadVentas;
    private Double porcentajeImpuesto;
    private Double totalImpuesto;
    private Integer anioFiscal;
    private String nombreImpuesto; // Campo para "IVA"

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCantidadVentas() { return cantidadVentas; }
    public void setCantidadVentas(Integer cantidadVentas) { this.cantidadVentas = cantidadVentas; }

    public Double getPorcentajeImpuesto() { return porcentajeImpuesto; }
    public void setPorcentajeImpuesto(Double porcentajeImpuesto) { this.porcentajeImpuesto = porcentajeImpuesto; }

    public Double getTotalImpuesto() { return totalImpuesto; }
    public void setTotalImpuesto(Double totalImpuesto) { this.totalImpuesto = totalImpuesto; }

    public Integer getAnioFiscal() { return anioFiscal; }
    public void setAnioFiscal(Integer anioFiscal) { this.anioFiscal = anioFiscal; }

    public String getNombreImpuesto() { return nombreImpuesto; }
    public void setNombreImpuesto(String nombreImpuesto) { this.nombreImpuesto = nombreImpuesto; }
}
