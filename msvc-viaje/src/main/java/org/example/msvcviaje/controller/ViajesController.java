package org.example.msvcviaje.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcviaje.dto.IniciarReq;
import org.example.msvcviaje.dto.*;
import org.example.msvcviaje.entity.Viaje;
import org.example.msvcviaje.service.ViajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viajes")
@RequiredArgsConstructor
public class ViajesController {
    private final ViajeService service;

    @PostMapping
    public ResponseEntity<Viaje> iniciar(@RequestBody IniciarReq req){
        return ResponseEntity.status(201).body(service.iniciar(req.usuarioId(), req.monopatinId(), req.paradaInicioId()));
    }
    @PostMapping("/{id}/pausas")
    public Viaje pausar(@PathVariable Long id) {
        return service.pausar(id);
    }
    @PostMapping("/{id}/continuar")
    public Viaje continuar(@PathVariable Long id) {
        return service.continuar(id);
    }
    @PostMapping("/{id}/finalizar")
    public Viaje finalizar(@PathVariable Long id, @RequestBody FinalizarReq req){
        return service.finalizar(id, req.paradaFinId());
    }
    @GetMapping("/{id}") public Viaje obtener(@PathVariable Long id){ return service.obtener(id); }
}
