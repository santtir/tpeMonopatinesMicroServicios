package org.example.msvcviaje.service;
import org.example.msvcviaje.entity.Viaje;

public interface ViajeService {
    Viaje iniciar(Long usuarioId, Long monopatinId, Long paradaInicioId);
    Viaje pausar(Long id);
    Viaje continuar(Long id);
    Viaje finalizar(Long id, Long paradaFinId);
    Viaje obtener(Long id);
}
