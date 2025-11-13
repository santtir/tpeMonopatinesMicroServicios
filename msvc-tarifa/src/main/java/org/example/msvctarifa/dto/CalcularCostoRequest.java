package org.example.msvctarifa.dto;

public record CalcularCostoRequest(
        int minutos,
        int minutosPausa,
        double km
) {}
