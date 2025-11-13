package org.example.msvcreportes.repository;

import org.example.msvcreportes.document.FacturacionDiaria;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface FacturacionDiariaRepo extends MongoRepository<FacturacionDiaria, String> {
    List<FacturacionDiaria> findByFechaBetween(LocalDate desde, LocalDate hasta);
}
