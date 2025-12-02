package com.juanhiginio.microservicioGateway.controllers.GatewayClinicaControllers;
import com.juanhiginio.microservicioGateway.dto.PatientsDTO.ActualizarPatientDTO;
import com.juanhiginio.microservicioGateway.dto.PatientsDTO.CrearPatientDTO;
import com.juanhiginio.microservicioGateway.dto.PatientsDTO.PatientResponseDTO;
import com.juanhiginio.microservicioGateway.services.impl.GatewayGenoSentinelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; // Importación para validar el Body de entrada
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gatewayClinica/patients")
@Tag(name = "Pacientes", description = "Gestión de pacientes redirigida a NestJS")
@PreAuthorize("hasRole('user')")
public class PatientsController {

    private final GatewayGenoSentinelService gatewayService;
    private static final String NEST_PATH = "http://localhost:4000/patients";

    public PatientsController(GatewayGenoSentinelService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo paciente", description = "Mapea a POST /patients")
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody CrearPatientDTO dto) {
        PatientResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.POST,
                NEST_PATH,
                dto,
                PatientResponseDTO.class
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    @Operation(summary = "Listar todos los pacientes", description = "Mapea a GET /patients")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientResponseDTO[].class)))
    public ResponseEntity<String> findAllPatients() {
        String responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                NEST_PATH,
                null,
                String.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID (UUID)", description = "Mapea a GET /patients/:id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    public ResponseEntity<PatientResponseDTO> findPatientById(@PathVariable String id) {
        String url = NEST_PATH + "/" + id;
        PatientResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.GET,
                url,
                null,
                PatientResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar datos del paciente", description = "Mapea a PATCH /patients/:id")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientResponseDTO.class)))
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String id, @RequestBody ActualizarPatientDTO dto) {
        String url = NEST_PATH + "/" + id;
        PatientResponseDTO responseBody = gatewayService.ejecutarPeticion(
                HttpMethod.PATCH,
                url,
                dto,
                PatientResponseDTO.class
        );
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un paciente", description = "Mapea a DELETE /patients/:id")
    @ApiResponse(responseCode = "200", description = "Paciente eliminado exitosamente, retorna confirmación o el objeto eliminado")
    public ResponseEntity<String> deletePatient(@PathVariable String id) {
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
