package com.juanhiginio.microservicioGateway.services.impl;

import com.juanhiginio.microservicioGateway.services.IGatewayGenoSentinelService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GatewayGenoSentinelService implements IGatewayGenoSentinelService {

    private final WebClient webClient;

    public GatewayGenoSentinelService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    // Método genérico que devuelve objeto de tipo T
    public <T> T ejecutarPeticion(
            HttpMethod metodo,
            String url,
            Object body,
            Class<T> responseType // La clase a la que convertir la respuesta
    ) {

        WebClient.RequestBodySpec requestSpec = webClient
                .method(metodo)
                .uri(url);

        // GET y DELETE NO usan body
        if (metodo == HttpMethod.GET || metodo == HttpMethod.DELETE) {

            return requestSpec
                    .retrieve()
                    .bodyToMono(responseType)
                    .block();
        } else {
            // POST / PUT / PATCH → con o sin body
            if (body != null) {
                return requestSpec
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(responseType)
                        .block();
            } else {
                return requestSpec
                        .retrieve()
                        .bodyToMono(responseType)
                        .block();
            }
        }
    }
}
