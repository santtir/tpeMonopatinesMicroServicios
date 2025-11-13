package org.example.msvccuenta.repository;
import org.example.msvccuenta.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByUsuarioId(Long usuarioId);
}
