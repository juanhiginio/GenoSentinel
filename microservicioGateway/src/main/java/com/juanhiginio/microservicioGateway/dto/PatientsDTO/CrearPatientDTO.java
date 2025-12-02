package com.juanhiginio.microservicioGateway.dto.PatientsDTO;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class CrearPatientDTO {

    @Schema(example = "Juan", description = "Nombre del paciente", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String firstName;

    @Schema(example = "Perez", description = "Apellido del paciente", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String lastName;

    @Schema(example = "1990-05-15", description = "Fecha de nacimiento (ISO format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // Validar el formato de fecha
    private String birthDate;

    @Schema(example = "Male", enumAsRef = true, allowableValues = {"Male", "Female", "Other"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "Male|Female|Other")
    @NotBlank
    private String gender;

    @Schema(example = "Activo", enumAsRef = true, allowableValues = {"Activo", "Seguimiento"}, defaultValue = "Activo", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "Activo|Seguimiento")
    @NotBlank
    private String status;
}
