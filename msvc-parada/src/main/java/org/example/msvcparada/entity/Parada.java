package org.example.msvcparada.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(nullable = false)
    private double lat;
    @Column(nullable = false)
    private double lng;

    @Column(nullable = false)
    private boolean activa;

    @Column(nullable = false)
    private int radioM; // radio de captación en metros
}
