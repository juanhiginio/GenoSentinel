package com.juanhiginio.microservicioGateway.dto.PatientVariantReportDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreatePatientVariantReportDTO {

    @Schema(description = "Identificador del paciente (desde otro microservicio)", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private UUID patientId;

    @Schema(description = "String ID de la variante genética asociada", example = "50f437ff7bd64fc4a49f444940cfe7a2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String variantId;

    @Schema(example = "2023-10-25", description = "Fecha de detección de la variante.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private LocalDate detectionDate;

    @Schema(example = "0.05", description = "Frecuencia alélica detectada (VAF %). Max 99.99", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99.99")
    private BigDecimal alleleFrequency;
}
