package com.registro.usuarios.controlador;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.modelo.Estado;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.EstadoRepositorio;
import com.registro.usuarios.servicio.ServicioServicioImpl;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {

    private final ServicioServicioImpl servicioServicio;
    private final UsuarioServicio usuarioServicio;
    private final EstadoRepositorio estadoRepositorio;

    public TecnicoController(ServicioServicioImpl servicioServicio,
                             UsuarioServicio usuarioServicio,
                             EstadoRepositorio estadoRepositorio) {
        this.servicioServicio = servicioServicio;
        this.usuarioServicio = usuarioServicio;
        this.estadoRepositorio = estadoRepositorio;
    }

    /**
     * ✅ Vista principal del técnico con dos tablas:
     * - Disponibles
     * - Asignados
     */
    @GetMapping("/home")
    public String mostrarHomeTecnico(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailTecnico = auth.getName();

        Usuario tecnico = usuarioServicio.buscarPorEmail(emailTecnico);

        List<Servicio> disponibles = servicioServicio.obtenerServiciosDisponiblesParaTecnico(emailTecnico);
        List<Servicio> asignados = servicioServicio.obtenerServiciosAsignados(emailTecnico);

        String nombreMostrar = (tecnico.getDatosPersonales() != null)
                ? tecnico.getDatosPersonales().getNombre()
                : tecnico.getEmail();

        List<Estado> estados = estadoRepositorio.findAll();

        model.addAttribute("tecnicoNombre", nombreMostrar);
        model.addAttribute("especializaciones", tecnico.getEspecializaciones());
        model.addAttribute("serviciosDisponibles", disponibles);
        model.addAttribute("serviciosAsignados", asignados);
        model.addAttribute("estados", estados);

        return "tecnico/home";
    }

    /**
     * ✅ Acción para actualizar un servicio individual
     */
    @PostMapping("/actualizar-servicio")
    public String actualizarServicio(@RequestParam("servicioId") Long servicioId,
                                     @RequestParam("estadoId") Long estadoId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailTecnico = auth.getName();

        servicioServicio.actualizarEstadoServicio(servicioId, estadoId, emailTecnico);

        return "redirect:/tecnico/home?actualizado";
    }
}
