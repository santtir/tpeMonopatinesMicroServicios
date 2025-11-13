package org.example.msvcviaje.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "viaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long monopatinId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private EstadoViaje estado;

    @Column(nullable = false)
    private Instant tsInicio;
    private Instant tsFin;

    @Column(nullable = false)
    private double km;

    @Column(nullable = false)
    private int minutos;

    @Column(nullable = false)
    private int minutosPausa;
    private Long paradaInicioId;

    @Column(nullable = false)
    private Long paradaFinId;

    @Column(nullable = false)
    private int costoTotalCentavos;
}
