package com.juanhiginio.microservicioGateway.dto.GeneDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de respuesta completa para un Gen.")
public class GeneResponseDTO {

    @Schema(description = "ID numérico del gen", example = "1")
    private Integer id;

    @Schema(example = "BRCA1", description = "Símbolo corto del gen, único.")
    private String symbol;

    @Schema(example = "Breast Cancer Gene 1", description = "Nombre completo del gen.")
    private String fullName;

    @Schema(example = "Involucrado en la reparación del ADN.", description = "Resumen de la función del gen.")
    private String functionSummary;
}
