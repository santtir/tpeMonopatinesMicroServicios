package org.example.msvcreportes.repository;

import org.example.msvcreportes.document.TopUsuarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TopUsuariosRepo extends MongoRepository<TopUsuarios, String> {
    List<TopUsuarios> findByPeriodo(String periodo);

}
