package org.example.msvcparada.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.repository.ParadaRepository;
import org.example.msvcparada.service.ParadaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParadaServiceImpl implements ParadaService {

    private final ParadaRepository repo;

    @Override
    public Parada crear(String nombre, double lat, double lng, int radioM) {
        if (radioM <= 0) radioM = 50;
        return repo.save(Parada.builder()
                .nombre(nombre).lat(lat).lng(lng)
                .radioM(radioM).activa(true).build());
    }

    @Override
    public Optional<Parada> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Parada> listar() {
        return repo.findAll();
    }

    @Override
    public Parada actualizar(Long id, String nombre, double lat, double lng, int radioM, Boolean activa) {
        var p = repo.findById(id).orElseThrow();
        if (nombre != null) p.setNombre(nombre);
        p.setLat(lat);
        p.setLng(lng);
        if (radioM > 0) p.setRadioM(radioM);
        if (activa != null) p.setActiva(activa);
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }


    //ia generate (para calcular si alguien está dentro de la parada.
    @Override
    public boolean dentroDeParada(Long paradaId, double lat, double lng) {
        var p = repo.findById(paradaId).orElseThrow();
        double R = 6371000; // m
        double dLat = Math.toRadians(lat - p.getLat());
        double dLng = Math.toRadians(lng - p.getLng());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(p.getLat())) * Math.cos(Math.toRadians(lat))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double d = 2 * R * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return d <= p.getRadioM();
    }
}
