package com.registro.usuarios.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.controlador.dto.ServicioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.EspecializacionRepositorio;
import com.registro.usuarios.repositorio.TipoServicioRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;
import com.registro.usuarios.servicio.ServicioServicioImpl;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private TipoServicioRepositorio tipoServicioRepositorio;

    @Autowired
    private EspecializacionRepositorio especializacionRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ServicioServicioImpl servicioServicio;

    @GetMapping("/home")
    public String mostrarHomeCliente(Model model, Principal principal) {
        // Obtener email del usuario autenticado
        String emailUsuario = principal.getName();

        // Obtener objeto Usuario para saludar por nombre
        Usuario cliente = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Agregar el nombre al modelo para el saludo personalizado
        model.addAttribute("clienteNombre", 
            cliente.getDatosPersonales() != null ? cliente.getDatosPersonales().getNombre() : "Cliente");


        // Pasar las especializaciones y tipos de servicio
        model.addAttribute("especializaciones", especializacionRepositorio.findAll());
        model.addAttribute("tipoServicios", tipoServicioRepositorio.findAll());

        // Pasar los servicios registrados por el cliente
        model.addAttribute("serviciosRegistrados", servicioServicio.obtenerServiciosPorUsuario(emailUsuario));

        return "cliente/home";
    }

    @PostMapping("/registrar-servicio")
    public String registrarServicio(ServicioRegistroDTO registroDTO, Principal principal) {
        String emailUsuario = principal.getName();
        servicioServicio.registrarServicio(emailUsuario, registroDTO);
        return "redirect:/cliente/home?exito";
    }
}
