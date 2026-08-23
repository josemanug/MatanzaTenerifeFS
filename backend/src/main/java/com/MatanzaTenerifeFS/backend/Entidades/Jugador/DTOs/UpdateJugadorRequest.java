package com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs;

public record UpdateJugadorRequest(
        String nombre,

        int dorsal,

        int telefono,

        String categoria,

        String dni
) {
}
