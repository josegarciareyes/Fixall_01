package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Especializacion;

@Repository
public interface EspecializacionRepositorio extends JpaRepository<Especializacion, Long> {

    

}
