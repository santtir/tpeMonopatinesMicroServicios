// controller/ReportesController.java
package org.example.msvcreportes.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcreportes.document.*;
import org.example.msvcreportes.service.ReportesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportesController {

    private final ReportesService svc;

    @GetMapping("/uso")
    public List<UsoDiario> uso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return svc.usoEntre(desde, hasta);
    }

    @GetMapping("/facturacion")
    public List<FacturacionDiaria> facturacion(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return svc.facturacionEntre(desde, hasta);
    }

    @GetMapping("/top-usuarios")
    public List<TopUsuarios> top(@RequestParam String periodo){
        return svc.topUsuarios(periodo);
    }
}
