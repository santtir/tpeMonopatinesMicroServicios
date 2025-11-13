package org.example.msvcviajes.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "pausa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pausa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long viajeId;

    @Column(nullable = false)
    private Instant tsInicio;

    @Column(nullable = false)
    private Instant tsFin;

    @Column(nullable = false)
    private int duracionMin;
}
