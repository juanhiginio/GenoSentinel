package com.juanhiginio.microservicioGateway.repositories;

import com.juanhiginio.microservicioGateway.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
