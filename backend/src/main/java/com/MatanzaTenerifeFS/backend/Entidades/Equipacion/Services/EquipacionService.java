package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionCreateDTO;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionResponse;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionUpdateDTO;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces.IEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Repositories.EquipacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
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
            equipacion.setCantidadTotal(totalEquipaciones(equipacionCreateDTO.stockPorTalla()));
            equipacion.setCantidadDisponible(equipacionesDisponibles(equipacionCreateDTO.stockPorTalla()));
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

    // Obtener los detalles de una Equipación
    public EquipacionResponse findById(int id){
        Equipacion equipacion = equipacionRepository.findById(id).orElseThrow();
        return mapEquipacionToDTO(equipacion);
    }

    // Actualizar una Equipación
    public void updateEquipacion(int id, EquipacionUpdateDTO equipacionUpdateDTO) {
        // Busco la equipacion por la ID
        Equipacion equipacion = equipacionRepository.findById(id).orElseThrow();

        // Obtención del stock antiguio
        Map<Talla, StockPorTalla> stockActual = equipacion.getStockPorTalla();

        // Stock Nuevo
        Map<Talla, StockPorTalla> stockNuevo = equipacionUpdateDTO.stockPorTalla();

        // Recorro las nuevas tallas
        for (Map.Entry<Talla, StockPorTalla> entry : stockNuevo.entrySet()) {

            // Obtengo la talla
            Talla talla = entry.getKey();
            // Obtengo el stock de la talla
            StockPorTalla nuevoStock = entry.getValue();

            // Buscamos la talla en el stock Antiguo
            StockPorTalla stockAnterior = stockActual.get(talla);

            // Comprobamos la existencia de la talla en el stock antigui
            if (stockAnterior == null) {

                // Si es una talla nueva, todo el stock es disponible
                nuevoStock.setCantidadDisponible(
                        nuevoStock.getCantidadTotal()
                );

            } else {

                // Obtengo el total antiguio
                int totalAnterior = stockAnterior.getCantidadTotal();
                // Obtengo el disponible antiguo
                int disponibleAnterior = stockAnterior.getCantidadDisponible();
                // Obtengo el nuevo total
                int nuevoTotal = nuevoStock.getCantidadTotal();

                // Calculo la diferencia entre el antiguo y el nuevo
                int diferencia = nuevoTotal - totalAnterior;

                // Si aumentan las unidades totales,
                // las nuevas unidades entran disponibles.
                if (diferencia > 0) {
                    nuevoStock.setCantidadDisponible(
                            disponibleAnterior + diferencia
                    );
                } else {
                    // Si disminuye el total, mantenemos
                    // la cantidad disponible actual.
                    nuevoStock.setCantidadDisponible(
                            disponibleAnterior
                    );
                }
            }
        }
        // Guardo el nuevo stock
        equipacion.setStockPorTalla(stockNuevo);


        // Set de los campos
        equipacion.setNombre(equipacionUpdateDTO.nombre());
        equipacion.setStockPorTalla(equipacionUpdateDTO.stockPorTalla());
        equipacion.setCantidadTotal(totalEquipaciones(equipacion.getStockPorTalla()));
        equipacion.setCantidadDisponible(equipacionesDisponibles(equipacion.getStockPorTalla()));
        equipacionRepository.save(equipacion);

    }


    /*
    *
    *   MÉTODOS ADICIONALES
    *
    * */

    // Método para calcular el total de equipaciones
    private int totalEquipaciones(Map<Talla, StockPorTalla> tallas){

        return tallas
                .values()
                .stream()
                .mapToInt(StockPorTalla::getCantidadTotal)
                .sum();
    }

    // Método para calcular las equipaciones disponibles
    private int equipacionesDisponibles(Map<Talla, StockPorTalla> tallas){

        return tallas
                .values()
                .stream()
                .mapToInt(StockPorTalla::getCantidadDisponible)
                .sum();
    }


    /*
    *
    *   MAPPERS DE LA ENTIDAD
    *
    * */


    // Mapper para pasar de Entidad a DTO.
    private EquipacionResponse mapEquipacionToDTO(Equipacion equipacion) {
        return new EquipacionResponse(
                equipacion.getEquipacionId(),
                equipacion.getCodEquipacion(),
                equipacion.getNombre(),
                equipacion.getCantidadTotal(),
                equipacion.getCantidadDisponible(),
                equipacion.getStockPorTalla()
        );
    }
}
