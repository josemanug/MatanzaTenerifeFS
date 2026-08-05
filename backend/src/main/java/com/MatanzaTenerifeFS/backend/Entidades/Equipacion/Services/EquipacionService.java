package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionResponse;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces.IEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Repositories.EquipacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipacionService implements IEquipacionService {

    private final EquipacionRepository equipacionRepository;

    public EquipacionService(EquipacionRepository equipacionRepository) {
        this.equipacionRepository = equipacionRepository;
    }

    // Obtener todas las equipaciones
    public List<EquipacionResponse> findAll(){
        return equipacionRepository.findAll().stream()
                .map(this::mapToEquipacionResponse)
                .collect(Collectors.toList());
    }

    // Mapper para pasar de entidad a DTO
    private EquipacionResponse mapToEquipacionResponse(Equipacion equipacion) {
        return new EquipacionResponse(
                equipacion.getCodEquipacion(),
                equipacion.getCantidadTotal(),
                equipacion.getStockPorTalla()
        );

    }
}
