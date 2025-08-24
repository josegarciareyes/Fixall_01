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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email; // Email del usuario

    @Column(nullable = false)
    private String password; // Contraseña del usuario

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private Set<Rol> roles; // Relación muchos a muchos con Rol


    // Relación muchos a muchos con Especializacion
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_especializaciones",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "especializacion_id")
    )
    private Set<Especializacion> especializaciones;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DatosPersonales datosPersonales;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuario tipoUsuario; // Relación con la entidad TipoUsuario

    // Constructor vacío (requerido por JPA)
    public Usuario() {
    }

    // Constructor con parámetros mínimos
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Constructor completo
    public Usuario(String email, String password, Set<Rol> roles, DatosPersonales datosPersonales, TipoUsuario tipoUsuario, Set<Especializacion> especializaciones) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.datosPersonales = datosPersonales;
        this.tipoUsuario = tipoUsuario;
        this.especializaciones = especializaciones;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<Especializacion> getEspecializaciones() {
        return especializaciones;
    }
    
    public void setEspecializaciones(Set<Especializacion> especializaciones) {
        this.especializaciones = especializaciones;
    }

}
