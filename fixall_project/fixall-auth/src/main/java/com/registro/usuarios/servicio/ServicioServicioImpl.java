package com.registro.usuarios.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ServicioRepositorio servicioRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final EspecializacionRepositorio especializacionRepositorio;
    private final TipoServicioRepositorio tipoServicioRepositorio;
    private final EstadoRepositorio estadoRepositorio;

    public ServicioServicioImpl(
            ServicioRepositorio servicioRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            EspecializacionRepositorio especializacionRepositorio,
            TipoServicioRepositorio tipoServicioRepositorio,
            EstadoRepositorio estadoRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.especializacionRepositorio = especializacionRepositorio;
        this.tipoServicioRepositorio = tipoServicioRepositorio;
        this.estadoRepositorio = estadoRepositorio;
    }

    /**
     * ✅ Registrar un nuevo servicio solicitado por un cliente
     */
    @Transactional
    public void registrarServicio(String emailUsuario, ServicioRegistroDTO registroDTO) {
        Usuario cliente = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + emailUsuario));

        Especializacion especializacion = especializacionRepositorio.findById(registroDTO.getEspecializacionId())
                .orElseThrow(() -> new RuntimeException("Especialización no encontrada"));

        TipoServicio tipoServicio = tipoServicioRepositorio.findById(registroDTO.getTipoServicioId())
                .orElseThrow(() -> new RuntimeException("Tipo de servicio no encontrado"));

        Estado estadoPendiente = estadoRepositorio.findByNombre("Pendiente")
                .orElseThrow(() -> new RuntimeException("Estado 'Pendiente' no encontrado"));

        // 🔹 Crear detalle del servicio con la descripción
        DetalleServicio detalle = new DetalleServicio();
        detalle.setDescripcion(registroDTO.getDescripcion());
        detalle.setFechaRegistro(LocalDateTime.now());

        // 🔹 Crear servicio asociado al cliente
        Servicio servicio = new Servicio(
                cliente,
                null, // técnico aún sin asignar
                tipoServicio,
                detalle,
                estadoPendiente,
                especializacion
        );

        servicioRepositorio.save(servicio);
    }

    /**
     * ✅ Actualizar estado de un servicio (se asigna si estaba pendiente)
     */
    @Transactional
    public void actualizarEstadoServicio(Long servicioId, Long estadoId, String emailTecnico) {
        Servicio servicio = servicioRepositorio.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + servicioId));

        Estado nuevoEstado = estadoRepositorio.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + estadoId));

        Usuario tecnico = usuarioRepositorio.findByEmail(emailTecnico)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado con email: " + emailTecnico));

        // 🆕 Si estaba libre, lo asigna al técnico que lo está actualizando
        if (servicio.getTecnico() == null) {
            servicio.setTecnico(tecnico);
        } else if (!servicio.getTecnico().getId().equals(tecnico.getId())) {
            throw new RuntimeException("El servicio ya fue tomado por otro técnico");
        }

        servicio.setEstado(nuevoEstado);
        servicio.setFechaUltimaActualizacion(LocalDateTime.now());

        servicioRepositorio.save(servicio);
    }

    /**
     * ✅ Obtener servicios disponibles (sin técnico asignado) para un técnico
     */
    public List<Servicio> obtenerServiciosDisponiblesParaTecnico(String emailTecnico) {
        Usuario tecnico = usuarioRepositorio.findByEmail(emailTecnico)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado con email: " + emailTecnico));
        return servicioRepositorio.findByEspecializacionInAndTecnicoIsNull(tecnico.getEspecializaciones());
    }

    /**
     * ✅ Obtener servicios asignados a un técnico
     */
    public List<Servicio> obtenerServiciosAsignados(String emailTecnico) {
        Usuario tecnico = usuarioRepositorio.findByEmail(emailTecnico)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado con email: " + emailTecnico));
        return servicioRepositorio.findByTecnico(tecnico);
    }

    /**
     * ✅ Obtener servicios por cliente (todos)
     */
    public List<Servicio> obtenerServiciosPorUsuario(String emailUsuario) {
        Usuario cliente = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + emailUsuario));
        return servicioRepositorio.findByUsuario(cliente);
    }

    /**
     * ✅ Filtrar servicios por estado para un cliente
     */
    public List<Servicio> obtenerServiciosPorEstado(String emailUsuario, String estadoNombre) {
        Usuario cliente = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + emailUsuario));
        return servicioRepositorio.findByUsuarioAndEstado_Nombre(cliente, estadoNombre);
    }
}
