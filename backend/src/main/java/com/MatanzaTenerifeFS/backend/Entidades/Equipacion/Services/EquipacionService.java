package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionCreateDTO;
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

    // Comprobar si existe un codigo de equipacion
    public boolean existsByCodEquipacion(String cod) {
        return equipacionRepository.existsByCodEquipacion(cod);
    }

    // Crear una nueva equipacion
    public void createEquipacion(EquipacionCreateDTO equipacionCreateDTO) throws Exception {

        if(!equipacionRepository.existsByCodEquipacion(equipacionCreateDTO.codEquipacion())){
            Equipacion equipacion = new Equipacion();

            equipacion.setCodEquipacion(equipacionCreateDTO.codEquipacion());
            equipacion.setNombre(equipacionCreateDTO.nombre());
            equipacion.setStockPorTalla(equipacionCreateDTO.stockPorTalla());

            int cantTotal = equipacionCreateDTO.stockPorTalla()
                    .values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            equipacion.setCantidadTotal(cantTotal);

            equipacionRepository.save(equipacion);
        } else {
            throw new Exception("Código ya existente");
        }


    }




    // Obtener la lista con todas las equipaciones
    public List<EquipacionResponse> findAll(){
        return equipacionRepository.findAll().stream()
                .map(this::mapEquipacionToDTO)
                .collect(Collectors.toList());
    }



/*
*
*   MAPPERS DE LA ENTIDAD
*
* */


    // Mapper para pasar de Entidad a DTO.
    private EquipacionResponse mapEquipacionToDTO(Equipacion equipacion) {
        return new EquipacionResponse(
                equipacion.getCodEquipacion(),
                equipacion.getNombre(),
                equipacion.getCantidadTotal(),
                equipacion.getStockPorTalla()
        );
    }
}
