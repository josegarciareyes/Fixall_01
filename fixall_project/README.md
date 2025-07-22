# Fixall

Fixall es un sistema web diseñado para conectar Clientes y Técnicos, facilitando la gestión y actualización de servicios técnicos en tiempo real.


# Tabla de Contenidos
1. Descripción General
2. Características Principales
3. Tecnologías Utilizadas
4. Instalación
5. Uso
6. Configuración de la Base de Datos
7. Estructura del Proyecto
8. Autores

------------------------------------------------------------------------------------------------------------------------------------------------------

# 1. Descripción General

Fixall permite a los usuarios:

- Registrar servicios técnicos como reparaciones, instalaciones, mantenimiento y diagnósticos.
- Gestionar servicios y actualizar estados en tiempo real.
- Dividir roles de usuario en Cliente y Técnico para personalizar la experiencia.

El proyecto fue desarrollado como parte del bootcamp de Talento Tech, nivel Programa Integrador, bajo la supervisión del profesor **Eder Lara Trujillo**.

------------------------------------------------------------------------------------------------------------------------------------------------------

# 2. Características Principales

- Autenticación y autorización de usuarios.
- Gestión de servicios técnicos dividida por roles (Cliente y Técnico).
- Actualización de estados en tiempo real.
- Interfaz responsiva utilizando Bootstrap y Thymeleaf.
- Sistema de gestión basado en roles y tipos de usuario.
   
------------------------------------------------------------------------------------------------------------------------------------------------------

# 3. Tecnologías Utilizadas
   
## Backend:
- Java 21
- Spring Boot
- Hibernate y JPA

## Frontend:
La parte del frontend que tiene este repositorio es:
- Thymeleaf
- Bootstrap 5.3

Adicionalmente se realizó otro repositorio para el frontend que no pudo ser enlazado utilizando:
- Next.js

## Base de Datos:
- MySQL

## Seguridad:
- Spring Security
- BCryptPasswordEncoder

## Servidor:
- Tomcat integrado en Spring Boot

------------------------------------------------------------------------------------------------------------------------------------------------------

# 4. Instalación

- ## Clonar el repositorio:
  https://github.com/josegarciareyes/Fixall

- ## Configurar la base de datos:

  * Ver el apartado Configuración de la Base de Datos para más detalles.

- ## Ejecutar el proyecto:
  
  * Instalar dependencias:
    ```  
     ./mvnw install
  
  * Levantar el servidor:
    ```
    ./mvnw spring-boot:run

------------------------------------------------------------------------------------------------------------------------------------------------------

# 5. Uso

## Cliente:

  - Registra servicios técnicos desde la vista cliente/home.
  - Visualiza sus servicios registrados y su estado actual.

## Técnico:

 - Gestiona los servicios desde la vista tecnico/home.
 - Actualiza el estado de los servicios según su progreso.


------------------------------------------------------------------------------------------------------------------------------------------------------

# 6. Configuración de la Base de Datos

   - ## Crea la base de datos:
     ```
     CREATE DATABASE registro_prueba_servicio;

   - ## Inserta datos iniciales:
     
     ### Roles:
     ```
     INSERT INTO rol (nombre) VALUES ('ADMIN'), ('USUARIO');
     ```
      
     ### Tipos de Usuario:
     ``` 
       INSERT INTO tipo_usuario (nombre) VALUES ('Cliente'), ('Técnico');
     ```
       
     ### Tipos de Servicio:
     ```
       INSERT INTO tipo_servicio (id, nombre) VALUES (1, 'Reparación');
     
       INSERT INTO tipo_servicio (id, nombre) VALUES (2, 'Instalación');
     
       INSERT INTO tipo_servicio (id, nombre) VALUES (3, 'Mantenimiento');
     
       INSERT INTO tipo_servicio (id, nombre) VALUES (4, 'Diagnóstico');
      ```
       
     ### Estados:
      ```
       INSERT INTO estado (nombre) VALUES ('Pendiente');
     
       INSERT INTO estado (nombre) VALUES ('En Proceso');
     
       INSERT INTO estado (nombre) VALUES ('Completado');
     
       INSERT INTO estado (nombre) VALUES ('Cancelado');
     ```
   - ## Configura las propiedades de conexión en application.properties:
     ```
      spring.datasource.url=jdbc:mysql://localhost:3306/registro_prueba_servicio
     
      spring.datasource.username=tuUsuario
     
      spring.datasource.password=tuContraseña
     

      spring.jpa.hibernate.ddl-auto=update
     
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
      ```
     
  ------------------------------------------------------------------------------------------------------------------------------------------------------

# 7. Estructura del Proyecto
   
   ## Controllers :
   Manejan las solicitudes y envían datos al frontend (ClienteController, TecnicoController, etc.).
   
   ## Servicios:
   Contienen la lógica de negocio (UsuarioServicioImpl, ServicioServicioImpl).
   
   ## Repositorios:
   Gestionan la comunicación con la base de datos (UsuarioRepositorio, ServicioRepositorio).
   
   ## Modelos:
   Entidades que representan las tablas de la base de datos (Usuario, Servicio, Estado).
   
   ## Frontend:
   Plantillas Thymeleaf para la interacción visual (login.html, registro.html, cliente/home.html, tecnico/home.html).
   
   ## Configuración:
   Ajustes de seguridad y base de datos en SecurityConfiguration y application.properties.
   

   ------------------------------------------------------------------------------------------------------------------------------------------------------
     
# 8.  Autores

- **Jose Armando Garcia Reyes - Developer**
- **Mateo Grisales Muñoz - Developer**



 
     

     





