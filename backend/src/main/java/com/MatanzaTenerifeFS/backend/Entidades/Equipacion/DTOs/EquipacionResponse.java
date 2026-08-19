package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import java.util.Map;

public record EquipacionResponse(

        int id,
         String codEquipacion,
         String nombre,
         int cantidadTotal,
         int cantidadDisponible,
         Map<Talla, StockPorTalla> stockPorTalla

) {
}
