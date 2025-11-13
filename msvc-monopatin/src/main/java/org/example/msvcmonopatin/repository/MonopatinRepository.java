package org.example.msvcmonopatin.repository;

import org.example.msvcmonopatin.entity.EstadoMonopatin;
import org.example.msvcmonopatin.entity.Monopatin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {
    Page<Monopatin> findByEstadoAndActivo(EstadoMonopatin estado, boolean activo, Pageable pageable);
    boolean existsByIdAndEstado(Long id, EstadoMonopatin estado);
}
