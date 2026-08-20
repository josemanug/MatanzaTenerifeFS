package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.CreateJugadorRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;

public interface IJugadorService {

    boolean existsByDNI(String dni);

    void createJugador(CreateJugadorRequest createJugadorRequest) throws Exception;

    void saveJugador(Jugador jugador);
}
