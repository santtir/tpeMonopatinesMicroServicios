package org.example.msvctarifa.dto;

public record CalcularCostoResponse(
        int costoTotalCentavos,
        int costoMinutosCent,
        int costoPausaCent,
        int costoKmCent,
        int recargoPausaCent
) {}
