package org.example.msvcmonopatin.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monopatin")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Monopatin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoMonopatin estado;     // LIBRE | EN_USO | MANTENIMIENTO

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(nullable = false)
    private boolean activo;            // alta/baja administrativa

    @Column(nullable = false)
    private double kmsTotales;

    @Column(nullable = false)
    private double kmsDesdeUltMant;

    @Column(nullable = false)
    private int viajesTotales;

    private java.time.LocalDate fechaUltMant;

    @Column(nullable = false)
    private boolean pendienteMant;

    @Column(nullable = false)
    private double kmsParaMant;        // cantidad de kms para requerir mantenimiento
}
