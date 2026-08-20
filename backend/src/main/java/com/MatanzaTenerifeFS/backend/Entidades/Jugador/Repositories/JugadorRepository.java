package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Repositories;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    boolean existsByDni(String dni);
}
