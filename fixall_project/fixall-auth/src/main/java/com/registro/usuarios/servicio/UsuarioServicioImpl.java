package com.registro.usuarios.servicio;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.DatosPersonales;
import com.registro.usuarios.modelo.Especializacion;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.TipoUsuario;
import com.registro.usuarios.modelo.TipoUsuarioEnum;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.DatosPersonalesRepositorio;
import com.registro.usuarios.repositorio.EspecializacionRepositorio;
import com.registro.usuarios.repositorio.RolRepositorio;
import com.registro.usuarios.repositorio.TipoUsuarioRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final DatosPersonalesRepositorio datosPersonalesRepositorio;
    private final RolRepositorio rolRepositorio;
    private final TipoUsuarioRepositorio tipoUsuarioRepositorio;
    private final EspecializacionRepositorio especializacionRepositorio;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio,
                               DatosPersonalesRepositorio datosPersonalesRepositorio,
                               RolRepositorio rolRepositorio,
                               TipoUsuarioRepositorio tipoUsuarioRepositorio,
                               EspecializacionRepositorio especializacionRepositorio,
                               BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.datosPersonalesRepositorio = datosPersonalesRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.tipoUsuarioRepositorio = tipoUsuarioRepositorio;
        this.especializacionRepositorio = especializacionRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario registrarUsuario(UsuarioRegistroDTO registroDTO) {
        // Buscar tipo de usuario
        TipoUsuario tipoUsuario = tipoUsuarioRepositorio.findById(registroDTO.getTipoUsuario().getId())
                .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado"));

        // Crear usuario base
        Usuario usuario = new Usuario();
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setTipoUsuario(tipoUsuario);

        // Guardar primero para evitar transitorios
        usuario = usuarioRepositorio.save(usuario);

        // Validar si es técnico
        TipoUsuarioEnum tipoEnum = TipoUsuarioEnum.fromId(tipoUsuario.getId());
        if (tipoEnum == TipoUsuarioEnum.TECNICO) {
            Set<Long> idsSeleccionados = registroDTO.getEspecializacionIds() == null
                    ? Collections.emptySet()
                    : registroDTO.getEspecializacionIds();

            Set<Especializacion> especializaciones = idsSeleccionados.stream()
                    .map(id -> especializacionRepositorio.findById(id)
                            .orElseThrow(() -> new RuntimeException("Especialización no encontrada: " + id)))
                    .collect(Collectors.toSet());

            usuario.setEspecializaciones(especializaciones);
        }

        // Rol por defecto
        Rol rolUsuario = rolRepositorio.findByNombre("USUARIO")
                .orElseThrow(() -> new RuntimeException("Rol USUARIO no encontrado"));
        usuario.setRoles(new HashSet<>(Collections.singleton(rolUsuario)));

        // Guardar usuario con roles/especializaciones
        usuario = usuarioRepositorio.save(usuario);

        // Crear datos personales y sincronizar
        DatosPersonales datosPersonales = new DatosPersonales(
                registroDTO.getNombre(),
                registroDTO.getCedula(),
                registroDTO.getDireccion(),
                registroDTO.getTelefono(),
                usuario
        );
        datosPersonalesRepositorio.save(datosPersonales);

        usuario.setDatosPersonales(datosPersonales);
        usuario = usuarioRepositorio.save(usuario);

        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(mapearAutoridadesRoles(usuario.getRoles()))
                .build();
    }

    private Set<? extends org.springframework.security.core.GrantedAuthority> mapearAutoridadesRoles(Set<Rol> roles) {
        return roles.stream()
                .map(rol -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));

        if (usuarioExistente.getDatosPersonales() != null && usuarioActualizado.getDatosPersonales() != null) {
            usuarioExistente.getDatosPersonales().setNombre(usuarioActualizado.getDatosPersonales().getNombre());
            usuarioExistente.getDatosPersonales().setCedula(usuarioActualizado.getDatosPersonales().getCedula());
            usuarioExistente.getDatosPersonales().setDireccion(usuarioActualizado.getDatosPersonales().getDireccion());
            usuarioExistente.getDatosPersonales().setTelefono(usuarioActualizado.getDatosPersonales().getTelefono());
        }

        return usuarioRepositorio.save(usuarioExistente);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorTipo(String tipoUsuario) {
        return usuarioRepositorio.findByTipoUsuario_Nombre(tipoUsuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }
}
