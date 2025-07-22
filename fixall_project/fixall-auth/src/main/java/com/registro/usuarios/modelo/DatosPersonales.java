package com.registro.usuarios.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "datos_personales")
public class DatosPersonales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Nombre del usuario

    @Column(unique = true, nullable = false)
    private String cedula; // Número de cédula único

    @Column(nullable = false)
    private String direccion; // Dirección

    @Column(nullable = false)
    private String telefono; // Teléfono

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Relación con Usuario

    // Relación muchos a muchos con Especializacion
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    name = "datos_personales_especializaciones",
    joinColumns = @JoinColumn(name = "datos_personales_id"),
    inverseJoinColumns = @JoinColumn(name = "especializacion_id")
    )
    private Set<Especializacion> especializaciones; // Especializaciones del usuario

    // Constructor vacío (requerido por JPA)
    public DatosPersonales() {
    }

    // Constructor
    public DatosPersonales(String nombre, String cedula, String direccion, String telefono, Usuario usuario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Set<Especializacion> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializaciones(Set<Especializacion> especializaciones) {
        this.especializaciones = especializaciones;
    }

    
}
