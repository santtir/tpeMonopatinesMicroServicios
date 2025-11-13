package org.example.msvctarifa.repository;

import org.example.msvctarifa.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    @Query("""
           select t from Tarifa t
           where t.vigenteDesde <= :ahora
             and (t.vigenteHasta is null or t.vigenteHasta >= :ahora)
           order by t.vigenteDesde desc
           """)
    Optional<Tarifa> findVigente(Instant ahora);
}
