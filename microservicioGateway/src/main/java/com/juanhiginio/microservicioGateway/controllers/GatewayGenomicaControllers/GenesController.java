package com.juanhiginio.microservicioGateway.controllers.GatewayGenomicaControllers;

import com.juanhiginio.microservicioGateway.dto.GeneDTO.CreateGeneDTO;
import com.juanhiginio.microservicioGateway.dto.GeneDTO.GeneResponseDTO;
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

@RestController
@RequestMapping("/gatewayGenomica/genes")
@Tag(name = "Genes (Django)", description = "Gestión de genes redirigida a Django App")
@PreAuthorize("hasRole('user')")
public class GenesController {

    private final GatewayGenoSentinelService gatewayService;
    private static final String DJANGO_PATH = "http://localhost:8000/genomic/genes/";

    public GenesController(GatewayGenoSentinelService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo gen", description = "Mapea a POST " + DJANGO_PATH)
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<GeneResponseDTO> createGene(@Valid @RequestBody CreateGeneDTO dto) {
        GeneResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.POST,
                DJANGO_PATH,
                dto,
                GeneResponseDTO.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    @Operation(summary = "Listar todos los genes", description = "Mapea a GET " + DJANGO_PATH)
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneResponseDTO[].class)))
    public ResponseEntity<String> findAllGenes() {
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                DJANGO_PATH,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar gen por ID numérico", description = "Mapea a GET " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Gen no encontrado")
    public ResponseEntity<GeneResponseDTO> findGeneById(@PathVariable String id) {
        String url = DJANGO_PATH + id + "/";
        GeneResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                url,
                null,
                GeneResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Reemplazar un gen existente (PUT completo)", description = "Mapea a PUT " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GeneResponseDTO.class)))
    public ResponseEntity<GeneResponseDTO> replaceGene(@PathVariable String id, @Valid @RequestBody CreateGeneDTO dto) {
        String url = DJANGO_PATH + id + "/";
        GeneResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.PUT,
                url,
                dto,
                GeneResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un gen", description = "Mapea a DELETE " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "204", description = "Gen eliminado exitosamente (No Content)")
    public ResponseEntity<Void> deleteGene(@PathVariable String id) {
        String url = DJANGO_PATH + id + "/";
        // El servicio ejecutará la petición DELETE, no necesitamos el body de respuesta
        gatewayService.ejecutarPeticion(
                HttpMethod.DELETE,
                url,
                null,
                Void.class // Esperamos un 204 No Content
        );
        return ResponseEntity.noContent().build();
    }
}
