package com.juanhiginio.microservicioGateway.services;

import org.springframework.http.HttpMethod;

public interface IGatewayGenoSentinelService {
    // Definimos un método genérico <T> que devuelve un objeto de tipo T
    <T> T ejecutarPeticion(
            HttpMethod metodo,
            String urlDestino,
            Object body, // El body a enviar (DTO o null)
            Class<T> responseType // La clase del objeto que se espera de vuelta
    );
}