package com.juanhiginio.microservicioGateway.dto.ClinicalRecordsDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "DTO de respuesta completa de Registro Clínico, incluyendo el ID generado.")
public class ClinicalRecordResponseDTO {

    @Schema(description = "Identificador único (UUID) del registro clínico", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;

    @Schema(description = "Identificador único (UUID) del paciente", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID patientId;

    @Schema(example = "1", description = "ID numérico del tipo de tumor existente", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer tumorTypeId;

    @Schema(example = "2023-10-20", description = "Fecha de Diagnostico del paciente")
    private String diagnosisDate;

    @Schema(example = "IIA", enumAsRef = true, allowableValues = {"IIA", "IV"}, description = "Estado del paciente")
    private String stage;

    @Schema(example = "Quimioterapia estándar", description = "Tratamiento asignado al paciente especifico")
    private String treatmentProtocol;

}
