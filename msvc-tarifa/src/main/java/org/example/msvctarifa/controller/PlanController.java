package org.example.msvctarifa.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvctarifa.entity.Plan;
import org.example.msvctarifa.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService service;

    @PostMapping
    public ResponseEntity<Plan> crear(@RequestBody Plan p){
        return ResponseEntity.status(201).body(service.crear(p));
    }

    @GetMapping
    public List<Plan> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> obtener(@PathVariable Long id){
        return ResponseEntity.of(service.obtener(id));
    }

    @PutMapping("/{id}")
    public Plan actualizar(@PathVariable Long id, @RequestBody Plan in){
        return service.actualizar(id, in);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
