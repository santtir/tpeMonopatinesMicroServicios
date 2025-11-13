package org.example.msvcviaje.repository;
import org.example.msvcviaje.entity.EstadoViaje;
import org.example.msvcviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    // check si tiene viaje activo

    boolean existsByUsuarioIdAndEstadoIn(Long usuarioId, Collection<EstadoViaje> estados);
    boolean existsByMonopatinIdAndEstadoIn(Long monopatinId, Collection<EstadoViaje> estados);
}
