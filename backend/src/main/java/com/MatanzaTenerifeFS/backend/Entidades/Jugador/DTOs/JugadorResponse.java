package com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs;


import java.util.List;

public record JugadorResponse(
        int playerId,
        String nombre,
        int dorsal,
        int telefono,
        String categoria,
        String dni,
        List<EquipacionAsignadaResponse> equipacionAsignadaResponse
) {
}
