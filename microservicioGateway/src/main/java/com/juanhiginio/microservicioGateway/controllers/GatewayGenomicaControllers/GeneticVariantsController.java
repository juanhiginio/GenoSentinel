package com.juanhiginio.microservicioGateway.controllers.GatewayGenomicaControllers;

import com.juanhiginio.microservicioGateway.dto.GeneticVariantDTO.CreateGeneticVariantDTO;
import com.juanhiginio.microservicioGateway.dto.GeneticVariantDTO.GeneticVariantResponseDTO;
import com.juanhiginio.microservicioGateway.services.impl.GatewayGenoSentinelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/gatewayGenomica/genetic-variants")
@Tag(name = "Variantes Genéticas (Django)", description = "Gestión de variantes genéticas redirigida a Django App")
@PreAuthorize("hasRole('user')")
public class GeneticVariantsController {

    private final GatewayGenoSentinelService gatewayService;
    // URL Base correcta para Django
    private static final String DJANGO_PATH = "http://localhost:8000/genomic/variants/";

    public GeneticVariantsController(GatewayGenoSentinelService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva variante genética", description = "Mapea a POST " + DJANGO_PATH)
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneticVariantResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<GeneticVariantResponseDTO> createVariant(@Valid @RequestBody CreateGeneticVariantDTO dto) {
        GeneticVariantResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.POST,
                DJANGO_PATH,
                dto,
                GeneticVariantResponseDTO.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    @Operation(summary = "Listar todas las variantes genéticas", description = "Mapea a GET " + DJANGO_PATH)
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneticVariantResponseDTO[].class)))
    public ResponseEntity<String> findAllVariants() {
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                DJANGO_PATH,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar variante genética por ID (String)", description = "Mapea a GET " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneticVariantResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Variante no encontrada")
    public ResponseEntity<GeneticVariantResponseDTO> findVariantById(@PathVariable String id) {
        String url = DJANGO_PATH + id + "/";
        GeneticVariantResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                url,
                null,
                GeneticVariantResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Reemplazar una variante existente (PUT completo)", description = "Mapea a PUT " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneticVariantResponseDTO.class)))
    public ResponseEntity<GeneticVariantResponseDTO> replaceVariant(@PathVariable String id, @Valid @RequestBody CreateGeneticVariantDTO dto) {
        String url = DJANGO_PATH + id + "/";
        GeneticVariantResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.PUT,
                url,
                dto,
                GeneticVariantResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una variante genética", description = "Mapea a DELETE " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "204", description = "Variante eliminada exitosamente (No Content)")
    public ResponseEntity<Void> deleteVariant(@PathVariable String id) {
        String url = DJANGO_PATH + id + "/";
        gatewayService.ejecutarPeticion(
                HttpMethod.DELETE,
                url,
                null,
                Void.class
        );
        return ResponseEntity.noContent().build();
    }
}
