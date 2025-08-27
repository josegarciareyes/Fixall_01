package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Especializacion;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.modelo.Usuario;

@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {

    /**
     * ✅ Buscar todos los servicios de un cliente específico
     */
    List<Servicio> findByUsuario(Usuario usuario);

    /**
     * ✅ Buscar todos los servicios que correspondan a un conjunto de especializaciones
     *    (lo usamos para filtrar servicios según las especializaciones del técnico)
     */
    List<Servicio> findByEspecializacionIn(Iterable<Especializacion> especializaciones);

    /**
     * ✅ Buscar servicios disponibles (no asignados) para un conjunto de especializaciones
     */
    List<Servicio> findByEspecializacionInAndTecnicoIsNull(Iterable<Especializacion> especializaciones);

    /**
     * ✅ Buscar servicios asignados a un técnico específico
     */
    List<Servicio> findByTecnico(Usuario tecnico);

    /**
     * 🔹 (Opcional) Buscar servicios por estado y especialización
     */
    List<Servicio> findByEspecializacionInAndEstado_Nombre(Iterable<Especializacion> especializaciones, String estadoNombre);

    /**
     * 🔹 (Opcional) Buscar servicios por cliente y estado
     */
    List<Servicio> findByUsuarioAndEstado_Nombre(Usuario usuario, String estadoNombre);
}

