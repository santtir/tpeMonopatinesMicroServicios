package org.example.msvcfacturacion.service;

import org.example.msvcfacturacion.dto.RegistrarCobroRequest;
import org.example.msvcfacturacion.entity.*;

import java.util.List;
import java.util.Optional;

public interface CobrosService {
    MovimientoCobro registrar(RegistrarCobroRequest req);          // crea movimiento
    Optional<MovimientoCobro> porViaje(Long viajeId);              // consulta por viaje
    List<MovimientoCobro> porUsuario(Long usuarioId, EstadoCobro estado);
}
