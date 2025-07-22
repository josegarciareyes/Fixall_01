package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.DetalleServicio;


@Repository
public interface DetalleServicioRepositorio extends JpaRepository<DetalleServicio, Long> {

}
