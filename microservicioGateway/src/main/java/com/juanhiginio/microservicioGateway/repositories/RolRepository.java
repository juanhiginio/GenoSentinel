package com.juanhiginio.microservicioGateway.repositories;

import com.juanhiginio.microservicioGateway.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRoleName(String rolName);
}
