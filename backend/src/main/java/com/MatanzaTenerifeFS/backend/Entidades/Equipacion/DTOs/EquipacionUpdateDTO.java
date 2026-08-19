package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

import java.util.Map;

public record EquipacionUpdateDTO(
        String nombre,
        Map<Talla, StockPorTalla> stockPorTalla
) {
}
