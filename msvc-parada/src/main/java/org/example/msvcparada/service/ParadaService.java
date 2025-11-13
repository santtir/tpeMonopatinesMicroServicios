package org.example.msvcparada.service;

import org.example.msvcparada.entity.Parada;
import java.util.Optional;
import java.util.List;

public interface ParadaService {
    Parada crear(String nombre, double lat, double lng, int radioM);
    Optional<Parada> obtener(Long id);
    List<Parada> listar();
    Parada actualizar(Long id, String nombre, double lat, double lng, int radioM, Boolean activa);
    void eliminar(Long id);
    boolean dentroDeParada(Long paradaId, double lat, double lng);
}
