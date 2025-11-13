package org.example.msvctarifa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tarifa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant vigenteDesde;
    private Instant vigenteHasta; // null = abierta


    @Column(nullable = false)
    private int precioMinuto;
    @Column(nullable = false)
    private int precioMinPausa;
    @Column(nullable = false)
    private int precioKm;

    // reglas
    @Column(nullable = false)
    private int maxMinsPausa;
    @Column(nullable = false)
    private int recargoPausa; // fijo por viaje
}
