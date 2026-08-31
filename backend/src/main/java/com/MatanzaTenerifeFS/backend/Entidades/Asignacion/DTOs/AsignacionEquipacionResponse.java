package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.DTOs;


import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

public record AsignacionEquipacionResponse(
        String nombre,
        Talla talla

) {
}
