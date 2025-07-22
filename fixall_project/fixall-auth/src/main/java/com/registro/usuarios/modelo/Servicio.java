package com.registro.usuarios.modelo;




import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Relación con Usuario

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico; // Relación con Usuario (Técnico asignado al servicio)

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio; // Relación con TipoServicio

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_servicio_id", nullable = false)
    private DetalleServicio detalleServicio; // Relación con DetalleServicio

    @ManyToOne
    @JoinColumn(name = "especializacion_id", nullable = false)
    private Especializacion especializacion; 

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado; // Relación con Estado del servicio

    @Column(name = "fecha_ultima_actualizacion")
    private LocalDateTime fechaUltimaActualizacion;

    // Constructor vacío
    public Servicio() {
    }

    // Constructor completo
    public Servicio(Usuario usuario, Usuario tecnico, TipoServicio tipoServicio, DetalleServicio detalleServicio, Estado estado, Especializacion especializacion) {
        this.usuario = usuario;
        this.tecnico = tecnico; 
        this.tipoServicio = tipoServicio;
        this.detalleServicio = detalleServicio;
        this.estado = estado;
        this.especializacion = especializacion;
        this.fechaUltimaActualizacion = LocalDateTime.now(); 
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getTecnico() {
        return tecnico;
    }
    
    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Especializacion getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(Especializacion especializacion) {
        this.especializacion = especializacion;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }
}
