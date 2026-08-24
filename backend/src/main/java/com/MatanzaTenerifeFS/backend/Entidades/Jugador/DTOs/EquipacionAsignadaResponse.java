package com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

import java.time.LocalDateTime;

public record EquipacionAsignadaResponse(
        int equipacionId,
        String nombre,
        Talla talla,
        LocalDateTime fechaAsignacion
) {
}
