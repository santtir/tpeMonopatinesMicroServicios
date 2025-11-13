package org.example.msvcreportes.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcreportes.document.FacturacionDiaria;
import org.example.msvcreportes.document.TopUsuarios;
import org.example.msvcreportes.document.UsoDiario;
import org.example.msvcreportes.repository.FacturacionDiariaRepo;
import org.example.msvcreportes.repository.TopUsuariosRepo;
import org.example.msvcreportes.repository.UsoDiarioRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.Map;

@RestController
@RequestMapping("/reportes/ingest")
@RequiredArgsConstructor
public class ReportesIngestController {

    private final UsoDiarioRepo uso;
    private final FacturacionDiariaRepo fact;
    private final TopUsuariosRepo top;

    // a, e: viaje finalizado (materializa uso y top-usuarios)
    @PostMapping("/viaje-finalizado")
    public ResponseEntity<Void> viajeFinalizado(@RequestBody Map<String, Object> body) {
        // body: fecha(ISO), monopatinId, usuarioId, km(double), minutos(int), minutosPausa(int), costoTotalCentavos(int), periodo(opt), tipoUsuario(opt)
        var fecha = LocalDate.parse(((String)body.get("fecha")).substring(0,10));
        var monopatinId = ((Number) body.get("monopatinId")).longValue();
        var usuarioId = ((Number) body.get("usuarioId")).longValue();
        var km = ((Number) body.get("km")).doubleValue();
        var minutos = ((Number) body.get("minutos")).intValue();
        var minutosPausa = ((Number) body.get("minutosPausa")).intValue();
        var costo = ((Number) body.get("costoTotalCentavos")).intValue();

        // upsert uso_diario (por fecha + monopatinId + usuarioId)
        var key = fecha + "_" + monopatinId + "_" + usuarioId;
        var row = uso.findById(key).orElse(UsoDiario.builder()
                .id(key).fecha(fecha).monopatinId(monopatinId)
                .viajes(0).km(0).min(0).minPausa(0).totalFacturadoCent(0).build());
        row.setViajes(row.getViajes()+1);
        row.setKm(row.getKm()+km);
        row.setMin(row.getMin()+minutos);
        row.setMinPausa(row.getMinPausa()+minutosPausa);
        row.setTotalFacturadoCent(row.getTotalFacturadoCent()+costo);
        uso.save(row);

        // upsert top_usuarios (por periodo + usuarioId)
        String periodo = (String) body.getOrDefault("periodo",
                java.time.YearMonth.from(fecha).toString()); // "2025-11"
        String tid = periodo + "_" + usuarioId;

        var tu = top.findById(tid).orElse(org.example.msvcreportes.document.TopUsuarios.builder()
                .id(tid).periodo(periodo).usuarioId(usuarioId)
                .viajes(0).facturadoCentavos(0).build());
        tu.setViajes(tu.getViajes() + 1);
        tu.setFacturadoCentavos(tu.getFacturadoCentavos() + costo);
        top.save(tu);

        return ResponseEntity.accepted().build();
    }

    // d: cobro registrado (acumula facturacion diaria)
    @PostMapping("/cobro-registrado")
    public ResponseEntity<Void> cobroRegistrado(@RequestBody Map<String, Object> body){
        var fecha = LocalDate.parse(((String)body.get("fecha")).substring(0,10));
        var monto = ((Number) body.get("montoCentavos")).intValue();
        var row = fact.findById(fecha.toString()).orElse(FacturacionDiaria.builder()
                .id(fecha.toString()).fecha(fecha).totalCentavos(0).build());
        row.setTotalCentavos(row.getTotalCentavos() + monto);
        fact.save(row);
        return ResponseEntity.accepted().build();
    }
}
