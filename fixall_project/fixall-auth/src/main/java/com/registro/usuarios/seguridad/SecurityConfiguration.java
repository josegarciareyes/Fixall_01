package com.registro.usuarios.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.registro.usuarios.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

        private final CustomSuccessHandler successHandler; // Maneja la redirección personalizada después del login


        public SecurityConfiguration(@Lazy CustomSuccessHandler successHandler) {
            this.successHandler = successHandler;
        }



	@Autowired
    @Lazy
	private UsuarioServicio usuarioServicio;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

        @Bean // Define el filtro principal de seguridad
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeRequests() // Configuración de autorización de solicitudes
                    .antMatchers("/login", "/registro", "/css/**", "/js/**").permitAll() 
                    // Estas rutas son públicas y accesibles sin autenticación
                    .anyRequest().authenticated() 
                    // Cualquier otra solicitud requiere autenticación
                    .and()
                .formLogin() // Configuración para el formulario de inicio de sesión
                    .loginPage("/login") // Especifica la página de inicio de sesión personalizada
                    .successHandler(successHandler) 
                    // Utiliza un manejador personalizado para redirigir después de iniciar sesión
                    .permitAll() // Permite el acceso a la página de login
                    .and()
                .logout() // Configuración del logout
                    .logoutUrl("/logout") // Configura la URL para cerrar sesión
                    .logoutSuccessUrl("/login?logout") 
                    // Redirige a esta URL después de cerrar sesión
                    .permitAll() // Permite el logout sin autenticación
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
            return http.build(); // Construye la configuración
        }

        @Bean // Define el AuthenticationManager utilizado por Spring Security
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); 
        // Devuelve el administrador de autenticación configurado por Spring
    }

	
    }







