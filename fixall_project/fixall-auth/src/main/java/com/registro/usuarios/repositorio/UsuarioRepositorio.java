package com.registro.usuarios.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);

	List<Usuario> findByTipoUsuario_Nombre(String nombreTipoUsuario);
	
}
