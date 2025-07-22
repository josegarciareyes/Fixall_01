package com.registro.usuarios.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.repositorio.EstadoRepositorio;
import com.registro.usuarios.servicio.ServicioServicioImpl;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {

    
    @Autowired
    private ServicioServicioImpl servicioServicio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/home")
    public String mostrarHomeTecnico(Model model) {
        // Obtener todos los servicios solicitados
        model.addAttribute("serviciosSolicitados", servicioServicio.obtenerTodosLosServicios());

        // Pasar todos los estados disponibles al modelo
        model.addAttribute("estados", estadoRepositorio.findAll());

        return "tecnico/home";
    }

    @PostMapping("/actualizar-servicio")
    public String actualizarServicio(@RequestParam("servicioId") Long servicioId, 
                                     @RequestParam("estadoId") Long estadoId, 
                                     Principal principal) {
        // Obtener el email del técnico autenticado
        String emailTecnico = principal.getName();

        // Actualizar el estado solo si realmente ha cambiado
        servicioServicio.actualizarEstadosServicios(servicioId, estadoId, emailTecnico);

        return "redirect:/tecnico/home?actualizado";
    }

}
