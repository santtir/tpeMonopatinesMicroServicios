package org.example.msvcreportes.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcreportes.document.FacturacionDiaria;
import org.example.msvcreportes.document.UsoDiario;
import org.example.msvcreportes.repository.FacturacionDiariaRepo;
import org.example.msvcreportes.repository.TopUsuariosRepo;
import org.example.msvcreportes.repository.UsoDiarioRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportesQueryController {

    private final UsoDiarioRepo uso;
    private final FacturacionDiariaRepo fact;
    private final TopUsuariosRepo top;

    // a: uso de monopatines por km (con/sin pausa)
    @GetMapping("/uso-monopatines")
    public List<Map<String,Object>> usoMonopatines(
            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(defaultValue = "true") boolean incluirPausa) {

        var rows = uso.findByFechaBetween(desde, hasta);
        var grouped = rows.stream().collect(Collectors.groupingBy(UsoDiario::getMonopatinId));
        List<Map<String,Object>> out = new ArrayList<>();
        for (var e : grouped.entrySet()){
            long monoId = e.getKey();
            double km = e.getValue().stream().mapToDouble(UsoDiario::getKm).sum();
            int min = e.getValue().stream().mapToInt(UsoDiario::getMin).sum();
            int minPausa = e.getValue().stream().mapToInt(UsoDiario::getMinPausa).sum();
            int minTotales = incluirPausa ? min + minPausa : min;
            out.add(Map.of("monopatinId", monoId, "km", km, "min", min, "minPausa", minPausa, "minTotales", minTotales));
        }
        return out.stream()
                .sorted(Comparator.comparingDouble(m -> -((Double)((Map)m).get("km"))))
                .collect(Collectors.toList());
    }

    // c: monopatines con > X viajes en un año
    @GetMapping("/monopatines/mas-viajes")
    public List<Map<String, Object>> masViajes(@RequestParam int anio,
                                               @RequestParam int minViajes) {

        LocalDate desde = LocalDate.of(anio, 1, 1);
        LocalDate hasta = LocalDate.of(anio, 12, 31);

        var rows = uso.findByFechaBetween(desde, hasta);

        // suma de viajes por monopatín
        Map<Long, Integer> conteo = new java.util.HashMap<>();
        for (var r : rows) {
            conteo.merge(r.getMonopatinId(), r.getViajes(), Integer::sum);
        }

        // filtra, ordena desc por viajes y arma salida
        List<Map<String, Object>> out = new java.util.ArrayList<>();
        conteo.entrySet().stream()
                .filter(e -> e.getValue() >= minViajes)
                .sorted(java.util.Map.Entry.<Long, Integer>comparingByValue(java.util.Comparator.reverseOrder()))
                .forEach(e -> {
                    Map<String, Object> m = new java.util.HashMap<>();
                    m.put("monopatinId", e.getKey());
                    m.put("viajes", e.getValue());
                    out.add(m);
                });

        return out;
    }


    // d: total facturado en rango de meses
    @GetMapping("/facturacion/total")
    public Map<String,Integer> totalFacturado(@RequestParam int anio,
                                              @RequestParam int mesDesde,
                                              @RequestParam int mesHasta){
        var desde = LocalDate.of(anio, mesDesde, 1);
        var hasta = LocalDate.of(anio, mesHasta, YearMonth.of(anio, mesHasta).lengthOfMonth());
        var rows = fact.findByFechaBetween(desde, hasta);
        int total = rows.stream().mapToInt(FacturacionDiaria::getTotalCentavos).sum();
        return Map.of("totalCentavos", total);
    }

    // e: top usuarios por periodo (orden = viajes | facturacion)
    @GetMapping("/top-usuarios")
    public List<Map<String, Object>> topUsuarios(@RequestParam String periodo,
                                                 @RequestParam(defaultValue = "viajes") String ordenPor,
                                                 @RequestParam(defaultValue = "10") int limit) {

        var rows = top.findByPeriodo(periodo);

        java.util.Comparator<org.example.msvcreportes.document.TopUsuarios> cmp =
                "facturacion".equalsIgnoreCase(ordenPor)
                        ? java.util.Comparator.comparingInt(org.example.msvcreportes.document.TopUsuarios::getFacturadoCentavos).reversed()
                        : java.util.Comparator.comparingInt(org.example.msvcreportes.document.TopUsuarios::getViajes).reversed();

        java.util.List<java.util.Map<String, Object>> out = new java.util.ArrayList<>();
        rows.stream().sorted(cmp).limit(limit).forEach(tu -> {
            java.util.Map<String, Object> m = new java.util.HashMap<>();
            m.put("usuarioId", tu.getUsuarioId());
            m.put("viajes", tu.getViajes());
            m.put("facturadoCentavos", tu.getFacturadoCentavos());
            m.put("periodo", tu.getPeriodo());
            out.add(m);
        });

        return out;
    }

    // h: uso de un usuario en período
    @GetMapping("/uso-usuario")
    public Map<String,Object> usoUsuario(@RequestParam Long usuarioId,
                                         @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate desde,
                                         @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate hasta){
        var rows = uso.findByFechaBetween(desde, hasta).stream()
                .filter(r -> Objects.equals(r.getUsuarioId(), usuarioId)).toList();
        int viajes = rows.stream().mapToInt(org.example.msvcreportes.document.UsoDiario::getViajes).sum();
        double km = rows.stream().mapToDouble(org.example.msvcreportes.document.UsoDiario::getKm).sum();
        int min = rows.stream().mapToInt(org.example.msvcreportes.document.UsoDiario::getMin).sum();
        int minPausa = rows.stream().mapToInt(org.example.msvcreportes.document.UsoDiario::getMinPausa).sum();
        return Map.of("usuarioId", usuarioId, "viajes", viajes, "km", km, "min", min, "minPausa", minPausa);
    }
}
