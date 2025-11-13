package org.example.msvcmonopatin.dto;

import org.example.msvcmonopatin.entity.EstadoMonopatin;

public record CrearMonopatinReq(
        double lat,
        double lng,
        double kmsParaMant,
        EstadoMonopatin estado
) {}
