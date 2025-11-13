package org.example.msvctarifa.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvctarifa.dto.CalcularCostoRequest;
import org.example.msvctarifa.dto.CalcularCostoResponse;
import org.example.msvctarifa.entity.Tarifa;
import org.example.msvctarifa.repository.TarifaRepository;
import org.example.msvctarifa.service.TarifaService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {

    private final TarifaRepository repo;

    @Override
    public Tarifa crear(Tarifa t) {
        return repo.save(t);
    }

    @Override
    public Optional<Tarifa> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Tarifa> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Tarifa> vigente() {
        return repo.findVigente(Instant.now());
    }

    @Override
    public CalcularCostoResponse calcular(CalcularCostoRequest req) {
        Tarifa t = vigente().orElseThrow(() -> new IllegalStateException("No hay tarifa vigente"));
        int costoMin = req.minutos() * t.getPrecioMinuto();
        int minutosP = Math.min(req.minutosPausa(), t.getMaxMinsPausa());
        int costoPausa = minutosP * t.getPrecioMinPausa();
        int costoKm = (int) Math.round(req.km() * t.getPrecioKm());
        int recargo = (req.minutosPausa() > 0) ? t.getRecargoPausa() : 0;
        int total = costoMin + costoPausa + costoKm + recargo;
        return new CalcularCostoResponse(total, costoMin, costoPausa, costoKm, recargo);
    }
}
