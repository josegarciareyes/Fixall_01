package com.registro.usuarios.modelo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_servicio")
public class DetalleServicio {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion; // Detalles específicos del servicio solicitado

    @Column(nullable = false)
    private LocalDateTime fechaRegistro; // Fecha en que se registró el servicio

   @OneToOne(mappedBy = "detalleServicio", cascade = CascadeType.ALL)
    private Servicio servicio; // Relación con Servicio     

    // Constructor vacío
    public DetalleServicio() {
    }

    // Constructor con parámetros
    public DetalleServicio(String descripcion, LocalDateTime fechaRegistro) {
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    
}
