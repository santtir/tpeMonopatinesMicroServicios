package org.example.msvcparada.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcparada.dto.CrearParadaRequest;
import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.service.ParadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paradas")
@RequiredArgsConstructor
public class ParadaController {

    private final ParadaService service;

    @PostMapping
    public ResponseEntity<Parada> crear(@RequestBody CrearParadaRequest req) {
        var p = service.crear(req.nombre(), req.lat(), req.lng(), req.radioM() == null ? 50 : req.radioM());
        return ResponseEntity.status(201).body(p);
    }

    @GetMapping
    public List<Parada> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> obtener(@PathVariable Long id) {
        return ResponseEntity.of(service.obtener(id));
    }

    @PutMapping("/{id}")
    public Parada actualizar(@PathVariable Long id, @RequestBody CrearParadaRequest req,
                             @RequestParam(required = false) Boolean activa) {
        return service.actualizar(id, req.nombre(), req.lat(), req.lng(),
                req.radioM() == null ? 50 : req.radioM(), activa);
    }

    @GetMapping("/{id}/validar")
    public boolean dentro(@PathVariable Long id, @RequestParam double lat, @RequestParam double lng) {
        return service.dentroDeParada(id, lat, lng);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
