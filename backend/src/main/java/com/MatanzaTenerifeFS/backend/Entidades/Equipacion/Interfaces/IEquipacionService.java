package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionResponse;

import java.util.List;

public interface IEquipacionService {

    List<EquipacionResponse> findAll();
}
