package com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

import java.time.LocalDateTime;

public record AsignadasResponse(
        String nombre,
        Talla talla,
        LocalDateTime fecha
) {
}
