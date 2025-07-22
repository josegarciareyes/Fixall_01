package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.repositorio.EspecializacionRepositorio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	
    private final UsuarioServicio usuarioServicio;
    private final EspecializacionRepositorio especializacionRepositorio;

    // Constructor para inyectar el servicio de usuarios
    
    public RegistroControlador(UsuarioServicio usuarioServicio, EspecializacionRepositorio especializacionRepositorio) {
        this.usuarioServicio = usuarioServicio;
        this.especializacionRepositorio = especializacionRepositorio;
    }

    // Muestra el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuarioRegistroDTO", new UsuarioRegistroDTO()); // Agrega un objeto DTO al modelo
        model.addAttribute("especializaciones", especializacionRepositorio.findAll()); // Cargar especializaciones
        return "registro"; // Renderiza la plantilla "registro.html"
    }

    // Procesa el registro de un nuevo usuario
    @PostMapping("/registro")
    public String registrarUsuario(UsuarioRegistroDTO registroDTO) {
        // Llama al servicio para manejar el registro del usuario
        usuarioServicio.registrarUsuario(registroDTO);
        return "redirect:/registro?exito"; // Redirige al formulario con un indicador de éxito
    }
	
}
