package org.example.msvcfacturacion.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvcfacturacion.dto.RegistrarCobroRequest;
import org.example.msvcfacturacion.entity.*;
import org.example.msvcfacturacion.repository.MovimientoCobroRepository;
import org.example.msvcfacturacion.service.CobrosService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class CobrosServiceImpl implements CobrosService {

    private final MovimientoCobroRepository repo;

    @Override
    public MovimientoCobro registrar(RegistrarCobroRequest req) {
        // si ya existiera cobro de ese viaje, devolvemos el existente (o actualizamos)
        var existente = repo.findByViajeId(req.viajeId());
        if (existente.isPresent()) return existente.get();

        var mov = MovimientoCobro.builder()
                .viajeId(req.viajeId())
                .usuarioId(req.usuarioId())
                .monto(req.montoCentavos())
                .estado(EstadoCobro.COBRADO)
                .tsCreacion(Instant.now())
                .tsActualizacion(Instant.now())
                .build();
        return repo.save(mov);
    }

    @Override
    public Optional<MovimientoCobro> porViaje(Long viajeId) {
        return repo.findByViajeId(viajeId);
    }

    @Override
    public List<MovimientoCobro> porUsuario(Long usuarioId, EstadoCobro estado) {
        return estado == null ? repo.findByUsuarioId(usuarioId)
                : repo.findByUsuarioIdAndEstado(usuarioId, estado);
    }

}
