package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.Estado;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

import java.time.LocalDateTime;

public record JugadorAsignadoResponse(
        int playerId,
        String nombre,
        int dorsal,
        String categoria,
        Talla talla,
        LocalDateTime fechaAsignacion,
        Estado estado
) {
}
