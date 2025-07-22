package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Especializacion;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.modelo.Usuario;


@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {
    List<Servicio> findByUsuario(Usuario usuario);
    List<Servicio> findByEspecializacion(Especializacion especializacion);

}
