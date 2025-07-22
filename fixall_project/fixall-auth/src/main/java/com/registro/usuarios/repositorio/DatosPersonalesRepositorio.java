package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.DatosPersonales;

@Repository
public interface DatosPersonalesRepositorio extends JpaRepository<DatosPersonales, Long> {

}
