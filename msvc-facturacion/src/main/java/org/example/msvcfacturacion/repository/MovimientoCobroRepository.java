package org.example.msvcfacturacion.repository;

import org.example.msvcfacturacion.entity.MovimientoCobro;
import org.example.msvcfacturacion.entity.EstadoCobro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovimientoCobroRepository extends JpaRepository<MovimientoCobro, Long> {
    Optional<MovimientoCobro> findByViajeId(Long viajeId);
    List<MovimientoCobro> findByUsuarioId(Long usuarioId);
    List<MovimientoCobro> findByUsuarioIdAndEstado(Long usuarioId, EstadoCobro estado);
}
