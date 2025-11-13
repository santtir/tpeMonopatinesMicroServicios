// service/ReportesServiceImpl.java
package org.example.msvcreportes.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvcreportes.document.*;
import org.example.msvcreportes.repository.*;
import org.example.msvcreportes.service.ReportesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReportesService {
    private final UsoDiarioRepo uso;
    private final FacturacionDiariaRepo fact;
    private final TopUsuariosRepo top;

    @Override
    public List<UsoDiario> usoEntre(LocalDate d, LocalDate h) {
        return uso.findByFechaBetween(d, h);
    }

    @Override
    public List<FacturacionDiaria> facturacionEntre(LocalDate d, LocalDate h) {
        return fact.findByFechaBetween(d, h);
    }

    @Override
    public List<TopUsuarios> topUsuarios(String periodo) {
        return top.findByPeriodo(periodo);
    }
}
