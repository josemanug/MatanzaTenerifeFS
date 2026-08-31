package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.DTOs.AsignacionEquipacionResponse;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Interfaces.IAsignacionEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.AsignacionEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.Estado;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Repositories.AsignacionEquipacionRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Repositories.EquipacionRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignacionEquipacionService implements IAsignacionEquipacionService {

    private final JugadorRepository jugadorRepository;
    private final EquipacionRepository equipacionRepository;
    private final AsignacionEquipacionRepository asignacionEquipacionRepository;

    public AsignacionEquipacionService(JugadorRepository jugadorRepository, EquipacionRepository equipacionRepository, AsignacionEquipacionRepository asignacionEquipacionRepository) {
        this.jugadorRepository = jugadorRepository;
        this.equipacionRepository = equipacionRepository;
        this.asignacionEquipacionRepository = asignacionEquipacionRepository;
    }

    public void asignarEquipacion(int jugadorId,int equipacionId, Talla talla) {

        // Obtengo el jugador
        Jugador jugador = jugadorRepository.findById(jugadorId).orElseThrow(() ->
                        new RuntimeException("Jugador no encontrado"));

        // Obtengo la equipación
        Equipacion equipacion = equipacionRepository.findById(equipacionId).orElseThrow(() ->
                        new RuntimeException("Equipación no encontrada"));

        // Obtengo el stock de esa talla
        StockPorTalla stock = equipacion.getStockPorTalla().get(talla);

        // Compruebo que existe esa talla
        if(stock == null){
            throw new RuntimeException("La equipación no tiene stock para la talla " + talla);
        }

        // Compruebo disponibilidad
        if(stock.getCantidadDisponible() <= 0){
            throw new RuntimeException("No hay stock disponible para la talla " + talla);
        }

        // Descontamos una unidad
        stock.setCantidadDisponible(stock.getCantidadDisponible() - 1);

        AsignacionEquipacion asignacion = new AsignacionEquipacion(equipacion, jugador, talla);
        asignacion.setEstado(Estado.ENTREGADA);

        asignacionEquipacionRepository.save(asignacion);
    }

    public List<AsignacionEquipacionResponse> obtenerEquipacionesPendientes(int playerId) {
        return asignacionEquipacionRepository
                .findByJugador_PlayerIdAndEstado(playerId,Estado.ENTREGADA)
                .stream()
                .map(this::mapToAsignacionEquipacionResponse)
                .toList();
    }

    private AsignacionEquipacionResponse mapToAsignacionEquipacionResponse(AsignacionEquipacion asignacionEquipacion) {
        return new AsignacionEquipacionResponse(
                asignacionEquipacion.getEquipacion().getNombre(),
                asignacionEquipacion.getTalla()
        );

    }

}
