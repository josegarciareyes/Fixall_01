package com.registro.usuarios.repositorio;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Especializacion;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.modelo.Usuario;


@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Long> {

    List<Servicio> findByUsuario(Usuario usuario);

    List<Servicio> findByEspecializacion(Especializacion especializacion);

    // ✅ Nuevo método para técnicos: solo servicios PENDIENTES de su especialización
    @Query("SELECT s FROM Servicio s " +
           "WHERE s.estado.nombre = 'Pendiente' AND s.especializacion IN :especializaciones")
    List<Servicio> findPendientesPorEspecializaciones(@Param("especializaciones") Set<Especializacion> especializaciones);

}
