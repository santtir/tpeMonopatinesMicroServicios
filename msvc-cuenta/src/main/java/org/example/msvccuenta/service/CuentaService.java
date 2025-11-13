package org.example.msvccuenta.service;

import org.example.msvccuenta.entity.*;
import java.util.Optional;

public interface CuentaService {
    Usuario crearUsuario(String nombre, String email, String telefono);
    Optional<Usuario> obtenerUsuario(Long id);
    Usuario actualizarUsuario(Long id, String nombre, String telefono, EstadoUsuario estado);

    Cuenta crearCuenta(Long usuarioId, TipoCuenta tipo);
    Optional<Cuenta> obtenerCuentaPorUsuario(Long usuarioId);
    Cuenta cambiarEstado(Long usuarioId, EstadoCuenta estado);
    boolean estaHabilitado(Long usuarioId); // para iniciar viaje
}
