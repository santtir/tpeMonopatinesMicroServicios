package org.example.msvcmonopatin.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcmonopatin.dto.*;
import org.example.msvcmonopatin.entity.*;
import org.example.msvcmonopatin.service.MonopatinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatines")
@RequiredArgsConstructor
public class MonopatinController {

    private final MonopatinService service;

    @PostMapping
    public ResponseEntity<Monopatin> crear(@RequestBody CrearMonopatinReq req) {
        var m = service.crear(req.lat(), req.lng(), req.kmsParaMant(), req.estado());
        return ResponseEntity.status(201).body(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monopatin> obtener(@PathVariable Long id) {
        return ResponseEntity.of(service.obtener(id));
    }

    @GetMapping
    public Page<Monopatin> listar(
            @RequestParam(required = false) EstadoMonopatin estado,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.listar(estado, activo, PageRequest.of(page, size));
    }

    @PutMapping("/{id}/ubicacion")
    public Monopatin actualizarUbicacion(@PathVariable Long id, @RequestBody UpdateUbicacionReq req) {
        return service.actualizarUbicacion(id, req.lat(), req.lng());
    }

    @PutMapping("/{id}/estado")
    public Monopatin cambiarEstado(@PathVariable Long id, @RequestBody CambiarEstadoReq req) {
        return service.cambiarEstado(id, req.estado());
    }

    @PutMapping("/{id}/activar")
    public Monopatin activar(@PathVariable Long id, @RequestParam boolean activo) {
        return service.activar(id, activo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
