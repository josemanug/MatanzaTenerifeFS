package com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs;


public record JugadorResponse(
        int playerId,

        String nombre,

        int dorsal,

        int telefono,

        String categoria,

        String dni
) {
}
