package org.example.msvcreportes.repository;

import org.example.msvcreportes.document.UsoDiario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface UsoDiarioRepo extends MongoRepository<UsoDiario, String> {
    List<UsoDiario> findByFechaBetween(LocalDate desde, LocalDate hasta);
}
