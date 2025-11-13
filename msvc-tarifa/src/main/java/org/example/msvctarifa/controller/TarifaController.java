package org.example.msvctarifa.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvctarifa.dto.CalcularCostoRequest;
import org.example.msvctarifa.dto.CalcularCostoResponse;
import org.example.msvctarifa.dto.CrearTarifaRequest;
import org.example.msvctarifa.entity.Tarifa;
import org.example.msvctarifa.service.TarifaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/tarifas")
@RequiredArgsConstructor
public class TarifaController {

    private final TarifaService service;

    @PostMapping
    public ResponseEntity<Tarifa> crear(@RequestBody CrearTarifaRequest req) {
        var t = Tarifa.builder()
                .vigenteDesde(req.vigenteDesde() != null ? req.vigenteDesde() : Instant.now())
                .vigenteHasta(req.vigenteHasta())
                .precioMinuto(req.precioMinutoCent())
                .precioMinPausa(req.precioMinPausaCent())
                .precioKm(req.precioKmCent())
                .maxMinsPausa(req.maxMinsPausa())
                .recargoPausa(req.recargoPausaCent())
                .build();
        return ResponseEntity.status(201).body(service.crear(t));
    }

    @GetMapping
    public List<Tarifa> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> obtener(@PathVariable Long id) {
        return ResponseEntity.of(service.obtener(id));
    }

    @GetMapping("/vigente")
    public ResponseEntity<Tarifa> vigente() {
        return ResponseEntity.of(service.vigente());
    }

    // endpoint interno para msvc-viaje
    @PostMapping("/calcular")
    public CalcularCostoResponse calcular(@RequestBody CalcularCostoRequest req) {
        return service.calcular(req);
    }
}
