package com.registro.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.TipoServicio;

@Repository
public interface TipoServicioRepositorio extends JpaRepository<TipoServicio, Long> {
    Optional<TipoServicio> findByNombre(String nombre);
}

