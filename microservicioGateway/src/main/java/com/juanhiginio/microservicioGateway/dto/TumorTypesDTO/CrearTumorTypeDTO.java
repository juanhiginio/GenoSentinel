package com.juanhiginio.microservicioGateway.dto.TumorTypesDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearTumorTypeDTO {
    @Schema(example = "Cancer de Mama", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String name;

    @Schema(example = "Glandulas Mamarias", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String systemAffected;
}
