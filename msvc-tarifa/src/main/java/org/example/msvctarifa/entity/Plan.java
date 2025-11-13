package org.example.msvctarifa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nombre;

    // km incluidos por mes; si supera, se cobra un extra por km
    @Column(nullable = false)
    private int cupoKmMensual;

    // costo extra por km excedente
    @Column(nullable = false)
    private int costoExcesoKm;


}
