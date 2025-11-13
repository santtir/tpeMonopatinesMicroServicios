package org.example.msvcmonopatin.service;

import lombok.RequiredArgsConstructor;
import org.example.msvcmonopatin.entity.*;
import org.example.msvcmonopatin.repository.MonopatinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonopatinServiceImpl implements MonopatinService {

    private final MonopatinRepository repo;

    @Override
    public Monopatin crear(double lat, double lng, double kmsParaMant, EstadoMonopatin estado) {
        Monopatin m = Monopatin.builder()
                .estado(estado != null ? estado : EstadoMonopatin.LIBRE)
                .lat(lat).lng(lng).activo(true)
                .kmsTotales(0).kmsDesdeUltMant(0).viajesTotales(0)
                .fechaUltMant(LocalDate.now())
                .pendienteMant(false)
                .kmsParaMant(kmsParaMant > 0 ? kmsParaMant : 500)
                .build();
        return repo.save(m);
    }

    @Override public Optional<Monopatin> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<Monopatin> listar(EstadoMonopatin estado, Boolean activo, Pageable page) {
        if (estado != null && activo != null){
            return repo.findByEstadoAndActivo(estado, activo, page);
        }
        return repo.findAll(page);
    }

    @Override
    public Monopatin actualizarUbicacion(Long id, double lat, double lng) {
        Monopatin m = repo.findById(id).orElseThrow();
        m.setLat(lat); m.setLng(lng);
        return repo.save(m);
    }

    @Override
    public Monopatin cambiarEstado(Long id, EstadoMonopatin nuevo) {
        Monopatin m = repo.findById(id).orElseThrow();
        m.setEstado(nuevo);
        // si pasó a mantenimiento, marcamos pendiente
        if (nuevo == EstadoMonopatin.MANTENIMIENTO) m.setPendienteMant(true);
        return repo.save(m);
    }

    @Override
    public Monopatin activar(Long id, boolean activo) {
        Monopatin m = repo.findById(id).orElseThrow();
        m.setActivo(activo);
        return repo.save(m);
    }

    @Override
    public void eliminar(Long id) {
        Monopatin m = repo.findById(id).orElseThrow();
        m.setActivo(false);
        repo.save(m);
    }
}
