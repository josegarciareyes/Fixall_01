package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.TipoUsuario;
import com.registro.usuarios.repositorio.TipoUsuarioRepositorio;



@Service
public class TipoUsuarioServicio {

    private final TipoUsuarioRepositorio tipoUsuarioRepositorio;

   
    public TipoUsuarioServicio(TipoUsuarioRepositorio tipoUsuarioRepositorio) {
        this.tipoUsuarioRepositorio = tipoUsuarioRepositorio;
    }

    public List<TipoUsuario> obtenerTodosLosTipos() {
        return tipoUsuarioRepositorio.findAll();
    }

}
