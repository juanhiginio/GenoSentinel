package com.juanhiginio.microservicioGateway.dto;

import lombok.Data;

import java.util.List;

/**
 * CrearUsuarioOutDTO
 *
 * DTO utilizado como respuesta tras la creación de un nuevo usuario en el sistema GenoSentinel.
 * Contiene la información generada y confirmada por el sistema luego del registro exitoso
 * del usuario, incluyendo su identificador, nombre de usuario, email y rol asignado.
 *
 * Este objeto se devuelve típicamente como resultado de una operación POST
 * en el controlador correspondiente a la entidad Usuario.
 *
 * Ejemplo JSON de respuesta:
 * {
 *   "id": 1,
 *   "username": "Juan Pérez",
 *   "email": "juan.perez@genomebank.com",
 *   "rol": "User"
 * }
 *
 * Campos importantes:
 * - **rol:** puede ser "User".
 *
 * @author GenoSentinel
 * @version 1.0
 * @since 2025
 */

@Data
public class CrearUsuarioOutDTO {
    private String username;
    private String email;
    private Boolean isActive;
    private List<String> roles;
}
