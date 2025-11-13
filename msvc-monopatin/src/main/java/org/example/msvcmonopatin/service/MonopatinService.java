package org.example.msvcmonopatin.service;

import org.example.msvcmonopatin.entity.EstadoMonopatin;
import org.example.msvcmonopatin.entity.Monopatin;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonopatinService {
    Monopatin crear(double lat, double lng, double kmsParaMant, EstadoMonopatin estado);
    Optional<Monopatin> obtener(Long id);
    Page<Monopatin> listar(EstadoMonopatin estado, Boolean activo, Pageable page);
    Monopatin actualizarUbicacion(Long id, double lat, double lng);
    Monopatin cambiarEstado(Long id, EstadoMonopatin nuevo);
    Monopatin activar(Long id, boolean activo);
    void eliminar(Long id);
}
