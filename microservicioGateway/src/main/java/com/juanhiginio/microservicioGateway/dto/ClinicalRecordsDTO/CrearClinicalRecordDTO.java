package com.juanhiginio.microservicioGateway.dto.ClinicalRecordsDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class CrearClinicalRecordDTO {
    @Schema(description = "UUID del paciente existente", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull // Para UUID o Integer @NotNull, no @NotBlank para válidar que deba venir el dato
    private UUID patientId; // Tipo UUID en Java

    @Schema(description = "ID numérico del tipo de tumor existente", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Integer tumorTypeId;

    @Schema(example = "2023-10-20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String diagnosisDate;

    @Schema(example = "IIA", enumAsRef = true, allowableValues = {"IIA", "IV"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "IIA|IV")
    @NotBlank
    private String stage;

    @Schema(example = "Quimioterapia estándar", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String treatmentProtocol;
}
