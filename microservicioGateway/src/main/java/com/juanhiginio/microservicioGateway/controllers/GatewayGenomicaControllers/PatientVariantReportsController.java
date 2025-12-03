package com.juanhiginio.microservicioGateway.controllers.GatewayGenomicaControllers;

import com.juanhiginio.microservicioGateway.dto.PatientVariantReportDTO.CreatePatientVariantReportDTO;
import com.juanhiginio.microservicioGateway.dto.PatientVariantReportDTO.PatientVariantReportResponseDTO;
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
@RequestMapping("/gatewayGenomica/patient-reports")
@Tag(name = "Reportes de Pacientes (Django)", description = "Gestión de reportes de variantes redirigida a Django App")
@PreAuthorize("hasRole('user')")
public class PatientVariantReportsController {

    private final GatewayGenoSentinelService gatewayService;
    // URL Base correcta para Django
    private static final String DJANGO_PATH = "http://localhost:8000/genomic/reports/";

    public PatientVariantReportsController(GatewayGenoSentinelService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo reporte de paciente", description = "Mapea a POST " + DJANGO_PATH)
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientVariantReportResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<PatientVariantReportResponseDTO> createReport(@Valid @RequestBody CreatePatientVariantReportDTO dto) {
        PatientVariantReportResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.POST,
                DJANGO_PATH,
                dto,
                PatientVariantReportResponseDTO.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    @Operation(summary = "Listar todos los reportes de pacientes", description = "Mapea a GET " + DJANGO_PATH)
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientVariantReportResponseDTO[].class)))
    public ResponseEntity<String> findAllReports() {
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                DJANGO_PATH,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reporte por ID (UUID)", description = "Mapea a GET " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientVariantReportResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    public ResponseEntity<PatientVariantReportResponseDTO> findReportById(@PathVariable String id) {
        String url = DJANGO_PATH + id + "/";
        PatientVariantReportResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                url,
                null,
                PatientVariantReportResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Reemplazar un reporte existente (PUT completo)", description = "Mapea a PUT " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientVariantReportResponseDTO.class)))
    public ResponseEntity<PatientVariantReportResponseDTO> replaceReport(@PathVariable String id, @Valid @RequestBody CreatePatientVariantReportDTO dto) {
        String url = DJANGO_PATH + id + "/";
        PatientVariantReportResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.PUT,
                url,
                dto,
                PatientVariantReportResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un reporte", description = "Mapea a DELETE " + DJANGO_PATH + ":id")
    @ApiResponse(responseCode = "204", description = "Reporte eliminado exitosamente (No Content)")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
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
