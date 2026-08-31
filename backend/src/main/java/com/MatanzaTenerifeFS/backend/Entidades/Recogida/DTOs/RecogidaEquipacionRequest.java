package com.MatanzaTenerifeFS.backend.Entidades.Recogida.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

public record RecogidaEquipacionRequest(
        int playerId,
        int asignacionId
) {}
