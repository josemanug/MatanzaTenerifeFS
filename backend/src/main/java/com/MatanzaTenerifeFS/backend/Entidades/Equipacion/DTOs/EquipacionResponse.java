package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import java.util.Map;

public record EquipacionResponse(

         String codEquipacion,
         int cantidadTotal,
         Map<Talla, Integer> stockPorTalla

) {
}
