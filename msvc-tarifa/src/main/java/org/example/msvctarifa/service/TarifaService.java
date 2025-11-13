package org.example.msvctarifa.service;

import org.example.msvctarifa.dto.CalcularCostoRequest;
import org.example.msvctarifa.dto.CalcularCostoResponse;
import org.example.msvctarifa.entity.Tarifa;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TarifaService {
    Tarifa crear(Tarifa t);
    Optional<Tarifa> obtener(Long id);
    List<Tarifa> listar();
    Optional<Tarifa> vigente();
    CalcularCostoResponse calcular(CalcularCostoRequest req, Instant at);

}
