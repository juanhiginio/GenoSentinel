package com.juanhiginio.microservicioGateway.dto.TumorTypesDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de respuesta completa de Tipo de Tumor, incluyendo el ID numérico.")
public class TumorTypeResponseDTO {

    @Schema(description = "Identificador único (ID) del tipo de tumor", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(example = "Cancer de Mama", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "Glandulas Mamarias", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String systemAffected;
}
