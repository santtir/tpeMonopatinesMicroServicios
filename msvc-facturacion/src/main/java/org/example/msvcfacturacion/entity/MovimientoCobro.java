package org.example.msvcfacturacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "movimiento_cobro",
        indexes = {@Index(columnList = "viajeId"), @Index(columnList = "usuarioId")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoCobro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long viajeId;
    @Column(nullable = false)
    private Long usuarioId;


    @Column(nullable = false)
    private int monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EstadoCobro estado;

    @Column(nullable = false)
    private Instant tsCreacion;
    private Instant tsActualizacion;
}
