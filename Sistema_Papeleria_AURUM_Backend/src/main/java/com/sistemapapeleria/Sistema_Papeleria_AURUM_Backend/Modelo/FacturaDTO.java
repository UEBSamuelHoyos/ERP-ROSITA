package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Modelo;
import java.util.Date;
import java.util.List;

public class FacturaDTO {
    private Long id;
    private Long clienteId;
    private Date fecha;
    private Double total;
    private String estado;
    private List<VentasDTO> ventas; // Opcional, si necesitas ventas asociadas

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<VentasDTO> getVentas() { return ventas; }
    public void setVentas(List<VentasDTO> ventas) { this.ventas = ventas; }
}