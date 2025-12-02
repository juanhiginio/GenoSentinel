package com.juanhiginio.microservicioGateway.controllers.GatewayClinicaControllers;

import com.juanhiginio.microservicioGateway.dto.TumorTypesDTO.CrearTumorTypeDTO;
import com.juanhiginio.microservicioGateway.dto.TumorTypesDTO.TumorTypeResponseDTO;
import com.juanhiginio.microservicioGateway.dto.TumorTypesDTO.ActualizarTumorTypeDTO;
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
@RequestMapping("/gatewayClinica/tumor-types")
@Tag(name = "Tipos de Tumor", description = "Gestión de catálogo de tumores redirigida a NestJS")
@PreAuthorize("hasRole('user')")
public class TumorTypesController {

    private final GatewayGenoSentinelService gatewayService;
    // URL base para el microservicio NestJS de tipos de tumor
    private static final String NEST_PATH = "http://localhost:4000/tumor-types";

    public TumorTypesController(GatewayGenoSentinelService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo tipo de tumor", description = "Mapea a POST /tumor-types")
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TumorTypeResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<TumorTypeResponseDTO> createTumorType(@Valid @RequestBody CrearTumorTypeDTO dto) {
        TumorTypeResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.POST,
                NEST_PATH,
                dto,
                TumorTypeResponseDTO.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    @Operation(summary = "Listar todos los tipos de tumor", description = "Mapea a GET /tumor-types")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TumorTypeResponseDTO[].class)))
    public ResponseEntity<String> findAllTumorTypes() {
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                NEST_PATH,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de tumor por ID numérico", description = "Mapea a GET /tumor-types/:id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TumorTypeResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Tipo de tumor no encontrado")
    public ResponseEntity<TumorTypeResponseDTO> findTumorTypeById(@PathVariable String id) {
        String url = NEST_PATH + "/" + id;
        TumorTypeResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                url,
                null,
                TumorTypeResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar datos del tipo de tumor", description = "Mapea a PATCH /tumor-types/:id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TumorTypeResponseDTO.class)))
    public ResponseEntity<TumorTypeResponseDTO> updateTumorType(@PathVariable String id, @RequestBody ActualizarTumorTypeDTO dto) {
        String url = NEST_PATH + "/" + id;
        TumorTypeResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.PATCH,
                url,
                dto,
                TumorTypeResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un tipo de tumor", description = "Mapea a DELETE /tumor-types/:id")
    @ApiResponse(responseCode = "200", description = "Tipo de tumor eliminado exitosamente")
    public ResponseEntity<String> deleteTumorType(@PathVariable String id) {
        String url = NEST_PATH + "/" + id;
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.DELETE,
                url,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }
}
