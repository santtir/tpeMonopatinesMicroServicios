package org.example.msvcfacturacion.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcfacturacion.dto.*;
import org.example.msvcfacturacion.entity.*;
import org.example.msvcfacturacion.service.CobrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturacion")
@RequiredArgsConstructor
public class CobrosController {

    private final CobrosService service;

    // usado por msvc-viaje cuando finaliza el viaje
    @PostMapping("/cobros")
    public ResponseEntity<MovimientoCobro> registrar(@RequestBody RegistrarCobroRequest req){
        var m = service.registrar(req);
        return ResponseEntity.status(201).body(m);
    }

    @GetMapping("/cobros/viajes/{viajeId}")
    public ResponseEntity<MovimientoCobro> porViaje(@PathVariable Long viajeId){
        return ResponseEntity.of(service.porViaje(viajeId));
    }

    @GetMapping("/cobros/usuarios/{usuarioId}")
    public List<MovimientoCobro> porUsuario(@PathVariable Long usuarioId,
                                            @RequestParam(required=false) EstadoCobro estado){
        return service.porUsuario(usuarioId, estado);
    }
}
