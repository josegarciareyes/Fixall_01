package com.registro.usuarios.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.controlador.dto.ServicioRegistroDTO;
import com.registro.usuarios.modelo.DetalleServicio;
import com.registro.usuarios.modelo.Especializacion;
import com.registro.usuarios.modelo.Estado;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.modelo.TipoServicio;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.EspecializacionRepositorio;
import com.registro.usuarios.repositorio.EstadoRepositorio;
import com.registro.usuarios.repositorio.ServicioRepositorio;
import com.registro.usuarios.repositorio.TipoServicioRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;


@Service
public class ServicioServicioImpl {
    
    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Autowired
    private TipoServicioRepositorio tipoServicioRepositorio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio; // Necesitamos el usuario para asociarlo al servicio

    @Autowired
    private EspecializacionRepositorio especializacionRepositorio; // Repositorio para Especializacion

    /**
     * Registrar un nuevo servicio por parte de un cliente.
     */
    public void registrarServicio(String emailUsuario, ServicioRegistroDTO registroDTO) {
        Usuario usuario = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        TipoServicio tipoServicio = tipoServicioRepositorio.findById(registroDTO.getTipoServicioId())
                .orElseThrow(() -> new RuntimeException("Tipo de Servicio no encontrado"));
    
        Estado estadoPendiente = estadoRepositorio.findByNombre("Pendiente")
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
    
        Especializacion especializacion = especializacionRepositorio.findById(registroDTO.getEspecializacionId())
                .orElseThrow(() -> new RuntimeException("Especialización no encontrada"));
    
        DetalleServicio detalleServicio = new DetalleServicio(registroDTO.getDescripcion(), LocalDateTime.now());
    
        Servicio servicio = new Servicio(usuario, null, tipoServicio, detalleServicio, estadoPendiente, especializacion);
    
        detalleServicio.setServicio(servicio);
    
        servicioRepositorio.save(servicio);
    }
    
    
    



    /**
     * Obtener los servicios registrados por un usuario específico.
     */
    public List<Servicio> obtenerServiciosPorUsuario(String emailUsuario) {
        // Buscar el usuario por su email
        Usuario usuario = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar servicios relacionados al usuario
        return servicioRepositorio.findByUsuario(usuario);
    }

    /**
     * Obtener todos los servicios solicitados.
     */
    public List<Servicio> obtenerTodosLosServicios() {
        // Recuperar todos los servicios solicitados
        return servicioRepositorio.findAll();
    }

    /**
     * Actualizar los estados de los servicios.
     * @param estados Map que contiene el ID del servicio y el ID del nuevo estado.
     */
    public void actualizarEstadosServicios(Long servicioId, Long estadoId, String emailTecnico) {
    
        Servicio servicio = servicioRepositorio.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Estado nuevoEstado = estadoRepositorio.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // Solo actualizar si el estado ha cambiado
        if (!servicio.getEstado().equals(nuevoEstado)) {
            Usuario tecnico = usuarioRepositorio.findByEmail(emailTecnico)
                    .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

            servicio.setEstado(nuevoEstado);
            servicio.setTecnico(tecnico);
            servicio.setFechaUltimaActualizacion(LocalDateTime.now()); // Solo actualiza si hubo cambio

            servicioRepositorio.save(servicio);
        }
    
}

}
