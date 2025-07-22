package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

     // Muestra la página de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Retorna la vista "login.html"
    }

}
