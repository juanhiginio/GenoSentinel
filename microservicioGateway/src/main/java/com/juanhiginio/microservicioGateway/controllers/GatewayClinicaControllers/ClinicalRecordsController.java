package com.juanhiginio.microservicioGateway.controllers.GatewayClinicaControllers;

import com.juanhiginio.microservicioGateway.dto.ClinicalRecordsDTO.ClinicalRecordResponseDTO;
import com.juanhiginio.microservicioGateway.dto.ClinicalRecordsDTO.CrearClinicalRecordDTO;
import com.juanhiginio.microservicioGateway.dto.ClinicalRecordsDTO.ActualizarClinicalRecordDTO;
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
@RequestMapping("/gatewayClinica/clinical-records")
@Tag(name = "Registros Clínicos", description = "Gestión de registros clínicos redirigida a NestJS")
@PreAuthorize("hasRole('user')")
public class ClinicalRecordsController {

    private final GatewayGenoSentinelService gatewayService;
    // URL base para el microservicio NestJS de historias clínicas
    private static final String NEST_PATH = "http://localhost:4000/clinical-records";

    public ClinicalRecordsController(GatewayGenoSentinelService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo registro clínico", description = "Mapea a POST /clinical-records")
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClinicalRecordResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<ClinicalRecordResponseDTO> createClinicalRecord(@Valid @RequestBody CrearClinicalRecordDTO dto) {
        ClinicalRecordResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.POST,
                NEST_PATH,
                dto,
                ClinicalRecordResponseDTO.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    @Operation(summary = "Listar todos los registros clínicos", description = "Mapea a GET /clinical-records")
    // Documentación Swagger para un array de respuestas
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClinicalRecordResponseDTO[].class)))
    public ResponseEntity<String> findAllClinicalRecords() {
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                NEST_PATH,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar registro clínico por ID (UUID)", description = "Mapea a GET /clinical-records/:id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClinicalRecordResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    public ResponseEntity<ClinicalRecordResponseDTO> findClinicalRecordById(@PathVariable String id) {
        String url = NEST_PATH + "/" + id;
        ClinicalRecordResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                url,
                null,
                ClinicalRecordResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar datos del registro clínico", description = "Mapea a PATCH /clinical-records/:id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClinicalRecordResponseDTO.class)))
    public ResponseEntity<ClinicalRecordResponseDTO> updateClinicalRecord(@PathVariable String id, @RequestBody ActualizarClinicalRecordDTO dto) {
        String url = NEST_PATH + "/" + id;
        ClinicalRecordResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.PATCH,
                url,
                dto,
                ClinicalRecordResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro clínico", description = "Mapea a DELETE /clinical-records/:id")
    @ApiResponse(responseCode = "200", description = "Registro eliminado exitosamente")
    public ResponseEntity<String> deleteClinicalRecord(@PathVariable String id) {
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
