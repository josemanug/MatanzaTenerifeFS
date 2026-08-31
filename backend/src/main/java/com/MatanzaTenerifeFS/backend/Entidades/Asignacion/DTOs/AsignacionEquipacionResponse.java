package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.DTOs;


import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

public record AsignacionEquipacionResponse(
        int id,
        int equipacionId,
        String nombre,
        Talla talla

) {
}
