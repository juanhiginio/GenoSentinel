package com.juanhiginio.microservicioGateway.dto;

import lombok.Data;

import java.util.List;

/**
 * CrearUsuarioInDTO
 *
 * DTO utilizado para la creación de un nuevo usuario dentro del sistema GenoSentinel.
 * Contiene la información necesaria para registrar un usuario, incluyendo sus datos personales,
 * credenciales de acceso y el rol asignado.
 *
 * Este objeto se utiliza como cuerpo de una solicitud POST en el controlador
 * correspondiente a la entidad Usuario.
 *
 * Ejemplo JSON de solicitud:
 * {
 *   "nombre": "Juan Pérez",
 *   "correoElectronico": "juan.perez@genomebank.com",
 *   "contrasena": "12345Segura!",
 *   "rol_id": 2
 * }
 *
 * Campos importantes:
 * - **email:** debe ser único por usuario.
 * - **rol_id:** referencia al rol asignado dentro del sistema (En este caso solo "User").
 *
 * @author GenoSentinel
 * @version 1.0
 * @since 2025
 */

@Data
public class CrearUsuarioInDTO {
    private String username;
    private String email;
    private String password_hash;
    private Boolean isActive;
    private List<String> roles;
}
