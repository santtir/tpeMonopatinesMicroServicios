package org.example.msvccuenta.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvccuenta.dto.*;
import org.example.msvccuenta.entity.*;
import org.example.msvccuenta.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService service;

    // Usuarios
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody CrearUsuarioRequest req){
        var u = service.crearUsuario(req.nombre(), req.email(), req.telefono());
        return ResponseEntity.status(201).body(u);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id){
        return ResponseEntity.of(service.obtenerUsuario(id));
    }

    @PutMapping("/usuarios/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody ActualizarUsuarioRequest req){
        var estado = (req.estado()==null) ? null : EstadoUsuario.valueOf(req.estado());
        return service.actualizarUsuario(id, req.nombre(), req.telefono(), estado);
    }

    // Cuenta
    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody CrearCuentaRequest req){
        var tipo = (req.tipo()==null) ? TipoCuenta.BASICA : TipoCuenta.valueOf(req.tipo());
        return ResponseEntity.status(201).body(service.crearCuenta(req.usuarioId(), tipo));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Long usuarioId){
        return ResponseEntity.of(service.obtenerCuentaPorUsuario(usuarioId));
    }


    @PutMapping("/{usuarioId}/estado")
    public Cuenta cambiarEstado(@PathVariable Long usuarioId, @RequestParam String estado){
        return service.cambiarEstado(usuarioId, EstadoCuenta.valueOf(estado));
    }

    // endpoint para validar habilitación (usado por msvc-viaje)
    @GetMapping("/{usuarioId}/habilitada")
    public boolean habilitada(@PathVariable Long usuarioId){
        return service.estaHabilitado(usuarioId);
    }
}
