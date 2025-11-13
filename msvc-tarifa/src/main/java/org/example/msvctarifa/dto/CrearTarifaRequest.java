package org.example.msvctarifa.dto;

import java.time.Instant;

public record CrearTarifaRequest(
        Instant vigenteDesde,
        Instant vigenteHasta,
        int precioMinutoCent,
        int precioMinPausaCent,
        int precioKmCent,
        int maxMinsPausa,
        int recargoPausaCent
) {}
