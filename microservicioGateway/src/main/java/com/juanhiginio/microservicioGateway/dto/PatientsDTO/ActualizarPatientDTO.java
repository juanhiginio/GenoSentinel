package com.juanhiginio.microservicioGateway.dto.PatientsDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ActualizarPatientDTO {
    @Schema(example = "Juan", description = "Nombre del paciente", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String firstName;

    @Schema(example = "Higinio", description = "Apellido del paciente", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastName;

    @Schema(example = "2006-11-06", description = "Fecha de nacimiento (ISO format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // Validar el formato de fecha simple
    private String birthDate;

    @Schema(example = "Male", enumAsRef = true, allowableValues = {"Male", "Female", "Other"}, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Pattern(regexp = "Male|Female|Other")
    private String gender;

    @Schema(example = "Activo", enumAsRef = true, allowableValues = {"Activo", "Seguimiento"}, defaultValue = "Activo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Pattern(regexp = "Activo|Seguimiento")
    private String status;
}
