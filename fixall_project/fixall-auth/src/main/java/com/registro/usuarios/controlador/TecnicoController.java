package com.registro.usuarios.controlador;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * ✅ Vista principal del técnico
     */
    @GetMapping("/home")
    public String mostrarHomeTecnico(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailTecnico = auth.getName();

        // Usar el método existente en tu interfaz/impl
        Usuario tecnico = usuarioServicio.buscarPorEmail(emailTecnico);

        // Puedes alternar entre ambos métodos según necesites
        // List<Servicio> servicios = servicioServicio.obtenerServiciosParaTecnicoConFiltro(emailTecnico);
        List<Servicio> servicios = servicioServicio.obtenerServiciosParaTecnico(emailTecnico);

        // Nombre a mostrar (si no hay datos personales, mostramos el email)
        String nombreMostrar = (tecnico.getDatosPersonales() != null)
                ? tecnico.getDatosPersonales().getNombre()
                : tecnico.getEmail();

        // Estados para el <select> de la vista
        List<Estado> estados = estadoRepositorio.findAll();

        model.addAttribute("tecnicoNombre", nombreMostrar);
        model.addAttribute("especializaciones", tecnico.getEspecializaciones());
        // La vista usa "serviciosSolicitados"
        model.addAttribute("serviciosSolicitados", servicios);
        model.addAttribute("estados", estados);

        return "tecnico/home";
    }

    /**
     * ✅ Acción para actualizar servicios desde la vista del técnico
     */
    @PostMapping("/actualizar-servicios")
    public String actualizarServicios(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailTecnico = auth.getName();

        Enumeration<String> nombresParametros = request.getParameterNames();
        while (nombresParametros.hasMoreElements()) {
            String nombreParametro = nombresParametros.nextElement();
            if (nombreParametro.startsWith("estado_")) {
                try {
                    Long servicioId = Long.parseLong(nombreParametro.substring(7));
                    Long estadoId = Long.parseLong(request.getParameter(nombreParametro));
                    servicioServicio.actualizarEstadosServicios(servicioId, estadoId, emailTecnico);
                } catch (NumberFormatException e) {
                    System.err.println("Error al procesar parámetro: " + nombreParametro);
                }
            }
        }
        return "redirect:/tecnico/home?actualizados";
    }
}
