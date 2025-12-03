package com.juanhiginio.microservicioGateway.dto.PatientVariantReportDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Schema(description = "DTO de respuesta completa para un Reporte de Variante de Paciente.")
public class PatientVariantReportResponseDTO {

    @Schema(description = "Identificador único (UUID) del reporte", example = "d7c285d6ff064a0c9bda16e2a2d886f6")
    private String id;

    @Schema(description = "Identificador del paciente (desde otro microservicio)", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    private UUID patientId;

    @Schema(description = "UUID de la variante genética asociada", example = "c1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    private UUID variantId; // Mapea el ForeignKey de Django (UUIDField)

    @Schema(example = "2023-10-25", description = "Fecha de detección de la variante.")
    private LocalDate detectionDate;

    @Schema(example = "0.05", description = "Frecuencia alélica detectada (VAF %).")
    private BigDecimal alleleFrequency;
}
