package com.registro.usuarios.controlador.dto;

import java.util.Set;

import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.TipoUsuario;

public class UsuarioRegistroDTO {

	private String email;
    private String password;
    private String nombre;
    private String cedula;
    private String direccion;
    private String telefono;
    private TipoUsuario tipoUsuario;
    private Set<Rol> roles; 
    private Set<Long> especializacionIds;

    // Constructor sin argumentos
    public UsuarioRegistroDTO() {
    }

    // Constructor con argumentos
    public UsuarioRegistroDTO(String email, String password, String nombre, String cedula, String direccion, String telefono, 
                                TipoUsuario tipoUsuario, Set<Rol> roles) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.roles = roles;
        
    }

    // Getters y Setters
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Long> getEspecializacionIds() {
        return especializacionIds;
    }
    
    public void setEspecializacionIds(Set<Long> especializacionIds) {
        this.especializacionIds = especializacionIds;
    }

}
