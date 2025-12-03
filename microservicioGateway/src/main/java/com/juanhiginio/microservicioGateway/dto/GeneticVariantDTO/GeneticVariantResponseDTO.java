package com.juanhiginio.microservicioGateway.dto.GeneticVariantDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
@Schema(description = "DTO de respuesta completa para una Variante Genética.")
public class GeneticVariantResponseDTO {

    @Schema(description = "Identificador único (String) de la variante", example = "50f437ff7bd64fc4a49f444940cfe7a2")
    private String id;

    @Schema(description = "ID del gen asociado", example = "1")
    private Integer geneId; // Mapea el ForeignKey de Django (AutoField)

    @Schema(example = "chr17", description = "Cromosoma donde se ubica.")
    private String chromosome;

    @Schema(example = "43044231", description = "Posición en el cromosoma.")
    private Integer position;

    @Schema(example = "A", description = "Base de referencia.")
    private String referenceBase;

    @Schema(example = "G", description = "Base alternante.")
    private String alternateBase;

    @Schema(example = "Missense", description = "Impacto funcional de la variante.")
    private String impact;
}
