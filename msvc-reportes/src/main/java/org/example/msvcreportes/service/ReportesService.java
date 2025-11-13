// service/ReportesService.java
package org.example.msvcreportes.service;

import org.example.msvcreportes.document.*;
import java.time.LocalDate;
import java.util.List;

public interface ReportesService {
    List<UsoDiario> usoEntre(LocalDate desde, LocalDate hasta);
    List<FacturacionDiaria> facturacionEntre(LocalDate desde, LocalDate hasta);
    List<TopUsuarios> topUsuarios(String periodo);
}
