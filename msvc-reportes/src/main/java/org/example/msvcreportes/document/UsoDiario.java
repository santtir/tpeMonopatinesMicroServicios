package org.example.msvcreportes.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document("uso_diario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsoDiario {
    @Id private String id;
    private LocalDate fecha;
    private Long monopatinId;
    private Long usuarioId;
    private int viajes;        // cantidad
    private double km;         // totales
    private int min;           // minutos en movimiento
    private int minPausa;
    private int totalFacturadoCent;
}
