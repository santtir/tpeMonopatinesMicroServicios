package org.example.msvccuenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvccuenta.entity.*;
import org.example.msvccuenta.repository.*;
import org.example.msvccuenta.service.CuentaService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service @RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final UsuarioRepository usuarios;
    private final CuentaRepository cuentas;

    @Override
    public Usuario crearUsuario(String nombre, String email, String telefono) {
        var u = Usuario.builder()
                .nombre(nombre).email(email).telefono(telefono)
                .estado(EstadoUsuario.ACTIVO)
                .fechaRegistro(Instant.now())
                .build();
        return usuarios.save(u);
    }

    @Override
    public java.util.Optional<Usuario> obtenerUsuario(Long id) {
        return usuarios.findById(id);
    }

    @Override
    public Usuario actualizarUsuario(Long id, String nombre, String telefono, EstadoUsuario estado) {
        var u = usuarios.findById(id).orElseThrow();
        if (nombre != null) u.setNombre(nombre);
        if (telefono != null) u.setTelefono(telefono);
        if (estado != null) u.setEstado(estado);
        return usuarios.save(u);
    }

    @Override
    public Cuenta crearCuenta(Long usuarioId, TipoCuenta tipo) {
        // una cuenta por usuario
        if (cuentas.findByUsuarioId(usuarioId).isPresent())
            throw new IllegalStateException("El usuario ya tiene cuenta");
        var c = Cuenta.builder()
                .usuarioId(usuarioId)
                .tipo(tipo != null ? tipo : TipoCuenta.BASICA)
                .estado(EstadoCuenta.HABILITADA)
                .inicioVigencia(Instant.now())
                .saldo(0)
                .build();
        return cuentas.save(c);
    }

    @Override
    public java.util.Optional<Cuenta> obtenerCuentaPorUsuario(Long usuarioId) {
        return cuentas.findByUsuarioId(usuarioId);
    }


    @Override
    public Cuenta cambiarEstado(Long usuarioId, EstadoCuenta estado) {
        var c = cuentas.findByUsuarioId(usuarioId).orElseThrow();
        c.setEstado(estado);
        return cuentas.save(c);
    }

    @Override
    public boolean estaHabilitado(Long usuarioId) {
        var u = usuarios.findById(usuarioId).orElseThrow();
        var c = cuentas.findByUsuarioId(usuarioId).orElseThrow();
        boolean dentroVigencia = c.getFinVigencia()==null || c.getFinVigencia().isAfter(Instant.now());
        return u.getEstado()==EstadoUsuario.ACTIVO
                && c.getEstado()==EstadoCuenta.HABILITADA
                && dentroVigencia;
    }
}
