package org.example.msvctarifa.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.msvctarifa.entity.Plan;
import org.example.msvctarifa.repository.PlanRepository;
import org.example.msvctarifa.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository repo;

    @Override
    public Plan crear(Plan plan) {
        return repo.save(plan);
    }

    @Override
    public Optional<Plan> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Plan> listar() {
        return repo.findAll();
    }

    @Override
    public Plan actualizar(Long id, Plan in) {
        Plan p = repo.findById(id).orElseThrow();
        if (in.getNombre() != null) p.setNombre(in.getNombre());
        if (in.getCupoKmMensual() > 0) p.setCupoKmMensual(in.getCupoKmMensual());
        if (in.getCostoExcesoKm() > 0) p.setCostoExcesoKm(in.getCostoExcesoKm());
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
