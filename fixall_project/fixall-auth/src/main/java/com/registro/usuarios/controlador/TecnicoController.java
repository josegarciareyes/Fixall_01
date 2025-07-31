package com.registro.usuarios.controlador;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Estado;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.repositorio.EstadoRepositorio;
import com.registro.usuarios.servicio.ServicioServicioImpl;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private ServicioServicioImpl servicioServicio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    /**
     * Página principal del técnico.
     * Muestra solo servicios relacionados con sus especializaciones y en estado "Pendiente".
     */
    @GetMapping("/home")
    public String mostrarHomeTecnico(Model model, Principal principal) {
        String emailTecnico = principal.getName();

        List<Servicio> serviciosFiltrados = servicioServicio.obtenerServiciosParaTecnico(emailTecnico);
        List<Estado> estadosDisponibles = estadoRepositorio.findAll();

        model.addAttribute("serviciosSolicitados", serviciosFiltrados);
        model.addAttribute("estados", estadosDisponibles);

        return "tecnico/home";
    }

    /**
     * Actualizar el estado de un servicio individual.
     * Este método asume que el formulario envía un par servicioId/estadoId por cada línea.
     */
    @PostMapping("/actualizar-servicio")
    public String actualizarServicio(@RequestParam("servicioId") Long servicioId,
                                     @RequestParam("estadoId") Long estadoId,
                                     Principal principal) {

        String emailTecnico = principal.getName();
        servicioServicio.actualizarEstadosServicios(servicioId, estadoId, emailTecnico);

        return "redirect:/tecnico/home?actualizado";
    }

}