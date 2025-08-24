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
     *    (esto lo usamos para filtrar servicios según las especializaciones del técnico)
     */
    List<Servicio> findByEspecializacionIn(Iterable<Especializacion> especializaciones);

    /**
     * 🔹 (Opcional) Buscar servicios asignados a un técnico específico
     *    Puede ser útil si quieres mostrar únicamente lo que ya tiene en curso ese técnico.
     */
    List<Servicio> findByTecnico(Usuario tecnico);

    /**
     * 🔹 (Opcional) Buscar servicios por estado y especialización
     *    Útil si más adelante deseas filtrar por pendientes, en proceso, etc.
     */
    List<Servicio> findByEspecializacionInAndEstado_Nombre(Iterable<Especializacion> especializaciones, String estadoNombre);
}
