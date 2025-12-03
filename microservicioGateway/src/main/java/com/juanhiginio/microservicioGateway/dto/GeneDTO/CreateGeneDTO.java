package com.juanhiginio.microservicioGateway.dto.GeneDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGeneDTO {

    @Schema(example = "BRCA1", requiredMode = Schema.RequiredMode.REQUIRED, description = "Símbolo corto del gen.")
    @NotBlank
    @Size(max = 10)
    private String symbol;

    @Schema(example = "Breast Cancer Gene 1", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nombre completo del gen.")
    @NotBlank
    @Size(max = 255)
    private String fullName;

    @Schema(example = "Involucrado en la reparación del ADN.", requiredMode = Schema.RequiredMode.REQUIRED, description = "Resumen de la función del gen.")
    @NotBlank
    private String functionSummary;
}
