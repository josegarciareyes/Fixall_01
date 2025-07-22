package com.registro.usuarios.seguridad;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.UsuarioRepositorio;




@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioRepositorio usuarioRepositorio;

    // Constructor para inyectar el repositorio de usuarios
    public CustomSuccessHandler(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Obtener el email del usuario autenticado
        String email = authentication.getName();

        // Buscar al usuario por su email
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        // Obtener el tipo de usuario directamente de Usuario
        String tipoUsuario = usuario.getTipoUsuario().getNombre();

        // Redirección basada en el tipo de usuario
        if ("Cliente".equalsIgnoreCase(tipoUsuario)) {
            response.sendRedirect("/cliente/home"); // Página para clientes
        } else if ("Técnico".equalsIgnoreCase(tipoUsuario)) {
            response.sendRedirect("/tecnico/home"); // Página para técnicos
        } else {
            response.sendRedirect("/default/home"); // Página por defecto si no se identifica el tipo
        }
    }
}
