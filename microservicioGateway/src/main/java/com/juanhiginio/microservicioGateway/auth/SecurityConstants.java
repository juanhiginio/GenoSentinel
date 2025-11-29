package com.juanhiginio.microservicioGateway.auth;

public class SecurityConstants {
    public static final String[] PUBLIC_URLS = {
            "/auth/login",
            "/auth/register",
            // Rutas de Swagger UI y OpenAPI docs (incluyendo el context-path /gateway)
            "/v3/api-docs/**",
            "/gateway/swagger-ui.html",
            "/gateway/swagger-ui/**",
            "/gateway/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };
}