package com.registro.usuarios.servicio;

// Interfaz UsuarioServicio define las operaciones de negocio.

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	// Método para registrar un nuevo usuario
	public Usuario registrarUsuario(UsuarioRegistroDTO registroDTO);
	
	// Método para obtener todos los usuarios
	public List<Usuario> listarUsuarios();

	Usuario buscarPorEmail(String email);

	Usuario actualizarUsuario(Long id, Usuario usuarioActualizado); // Actualización de usuarios

	List<Usuario> obtenerUsuariosPorTipo(String tipoUsuario);

	
}
