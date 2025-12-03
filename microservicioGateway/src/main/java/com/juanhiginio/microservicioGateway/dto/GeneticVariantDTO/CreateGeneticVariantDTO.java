package com.juanhiginio.microservicioGateway.dto.GeneticVariantDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGeneticVariantDTO {

    @Schema(description = "ID del gen asociado", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(1) // Asumimos IDs positivos
    private Integer geneId;

    @Schema(example = "chr17", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 5)
    private String chromosome;

    @Schema(example = "43044231", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(0)
    private Integer position;

    @Schema(example = "A", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 1, max = 1)
    private String referenceBase;

    @Schema(example = "G", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 1, max = 1)
    private String alternateBase;

    // Django usa choices, Spring usará un String validado por el backend de Django
    @Schema(example = "Missense", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 20)
    private String impact;
}
