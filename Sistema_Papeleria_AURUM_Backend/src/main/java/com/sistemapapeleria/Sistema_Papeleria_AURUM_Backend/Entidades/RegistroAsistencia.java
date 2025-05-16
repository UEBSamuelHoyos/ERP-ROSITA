package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registro_asistencia")
public class RegistroAsistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleados empleado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Empleados getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Date getFechaHora() { return fechaHora; }
    public void setFechaHora(Date fechaHora) { this.fechaHora = fechaHora; }
}
