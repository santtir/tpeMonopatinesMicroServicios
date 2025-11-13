package org.example.msvcviaje.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvcviaje.entity.*;
import org.example.msvcviaje.repository.ViajeRepository;
import org.example.msvcviaje.service.ViajeService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {
    private final ViajeRepository repo;

    @Override
    public Viaje iniciar(Long usuarioId, Long monopatinId, Long paradaInicioId) {
        var activos = List.of(EstadoViaje.INICIADO, EstadoViaje.PAUSADO);

        //checkeo que ni el usuario tenga un viaje activo, ni el monopatin esté en uso
        if (repo.existsByUsuarioIdAndEstadoIn(usuarioId, activos)) {
            throw new IllegalStateException("El usuario ya tiene un viaje activo");
        }
        if (repo.existsByMonopatinIdAndEstadoIn(monopatinId, activos)) {
            throw new IllegalStateException("El monopatín ya está en uso");
        }


        var v = Viaje.builder()
                .usuarioId(usuarioId).monopatinId(monopatinId)
                .estado(EstadoViaje.INICIADO).tsInicio(Instant.now())
                .km(0).minutos(0).minutosPausa(0)
                .paradaInicioId(paradaInicioId).paradaFinId(0L)
                .costoTotalCentavos(0).build();
        return repo.save(v);
    }

    @Override
    public Viaje pausar(Long id) {
        var v = repo.findById(id).orElseThrow();
        v.setEstado(EstadoViaje.PAUSADO);
        return repo.save(v);
    }

    @Override
    public Viaje continuar(Long id) {
        var v = repo.findById(id).orElseThrow();
        v.setEstado(EstadoViaje.INICIADO);
        return repo.save(v);
    }

    @Override
    public Viaje finalizar(Long id, Long paradaFinId) {
        var v = repo.findById(id).orElseThrow();
        v.setEstado(EstadoViaje.FINALIZADO);
        v.setTsFin(Instant.now());
        v.setParadaFinId(paradaFinId);
        return repo.save(v);
    }

    @Override
    public Viaje obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
