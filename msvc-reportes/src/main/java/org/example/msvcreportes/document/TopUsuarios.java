package org.example.msvcreportes.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("top_usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TopUsuarios {
    @Id private String id;
    private String periodo;
    private Long usuarioId;
    private int viajes;
    private int facturadoCentavos;
}
