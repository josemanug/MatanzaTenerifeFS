package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

import java.util.Map;

public record EquipacionCreateDTO(

        String codEquipacion,
        String nombre,
        Map<Talla, Integer> stockPorTalla
) {}
