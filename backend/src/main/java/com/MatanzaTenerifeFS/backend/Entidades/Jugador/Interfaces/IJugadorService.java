package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.CreateJugadorRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.JugadorResponse;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;

import java.util.List;

public interface IJugadorService {

    boolean existsByDNI(String dni);

    void createJugador(CreateJugadorRequest createJugadorRequest) throws Exception;

    void saveJugador(Jugador jugador);

    List<JugadorResponse> findAll();

    JugadorResponse findById(int id);
}
