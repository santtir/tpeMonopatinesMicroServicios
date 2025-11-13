package org.example.msvctarifa.service;

import org.example.msvctarifa.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    Plan crear(Plan plan);
    Optional<Plan> obtener(Long id);
    List<Plan> listar();
    Plan actualizar(Long id, Plan in);
    void eliminar(Long id);
}
