package org.example.msvcreportes.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document("facturacion_diaria")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FacturacionDiaria {
    @Id private String id;
    private LocalDate fecha;
    private int totalCentavos;
}