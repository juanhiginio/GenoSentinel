package com.juanhiginio.microservicioGateway.dto.PatientsDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
@Schema(description = "DTO de respuesta completa del Paciente, incluyendo el ID generado.")
public class PatientResponseDTO {

    @Schema(description = "Identificador único (UUID) del paciente", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;

    @Schema(example = "Juan", description = "Nombre del paciente")
    private String firstName;

    @Schema(example = "Perez", description = "Apellido del paciente")
    private String lastName;

    @Schema(example = "1990-05-15")
    private String birthDate;

    @Schema(example = "Male", enumAsRef = true, allowableValues = {"Male", "Female", "Other"})
    private String gender;

    @Schema(example = "Activo", enumAsRef = true, allowableValues = {"Activo", "Seguimiento"})
    private String status;

}
