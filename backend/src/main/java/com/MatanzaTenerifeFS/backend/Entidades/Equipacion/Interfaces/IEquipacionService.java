package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionCreateDTO;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionResponse;

import java.util.List;

public interface IEquipacionService {

    boolean existsByCodEquipacion(String cod);

    void createEquipacion(EquipacionCreateDTO equipacionCreateDTO) throws Exception;

    List<EquipacionResponse> findAll();

}
