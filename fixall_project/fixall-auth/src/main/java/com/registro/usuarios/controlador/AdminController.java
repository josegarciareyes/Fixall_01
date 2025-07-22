package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // Ruta principal para administradores
    @GetMapping("/home")
    public String homeAdmin() {
        return "admin/home"; // Retorna la vista "admin/home.html"
    }

}
