package com.registro.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Estado;

@Repository
public interface EstadoRepositorio extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNombre(String nombre);
}
