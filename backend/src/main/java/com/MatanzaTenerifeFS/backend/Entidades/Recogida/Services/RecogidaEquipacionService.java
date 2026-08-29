package com.MatanzaTenerifeFS.backend.Entidades.Recogida.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Repositories.EquipacionRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Repositories.JugadorRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Interfaces.IRecogidaEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Models.RecogidaEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Repositories.RecogidaEquipacionRepository;
import org.springframework.stereotype.Service;

@Service
public class RecogidaEquipacionService implements IRecogidaEquipacionService {

    private final RecogidaEquipacionRepository recogidaEquipacionRepository;
    private final JugadorRepository jugadorRepository;
    private final EquipacionRepository equipacionRepository;

    public RecogidaEquipacionService(RecogidaEquipacionRepository recogidaEquipacionRepository, JugadorRepository jugadorRepository, EquipacionRepository equipacionRepository) {
        this.recogidaEquipacionRepository = recogidaEquipacionRepository;
        this.jugadorRepository = jugadorRepository;
        this.equipacionRepository = equipacionRepository;
    }

    // Método para la recogida de equipaciones
    public void recogerEquipacion(int jugadorId, int equipacionId, Talla talla) throws Exception {

        // Obtener el jugador
        Jugador jugador = jugadorRepository.findById(jugadorId).orElseThrow(() ->
                new RuntimeException("Jugador no encontrado"));

        // Obtengo la equipacion
        Equipacion equipacion = equipacionRepository.findById(equipacionId).orElseThrow(() ->
                new RuntimeException("Equipación no encontrada"));

        // Obtengo el stock de esa talla
        StockPorTalla stock = equipacion.getStockPorTalla().get(talla);

        // Compruebo que existe esa talla
        if(stock == null){
            throw new RuntimeException("La equipación no tiene stock para la talla " + talla);
        }

        if(stock.getCantidadDisponible() < stock.getCantidadTotal()){
            // Aumento la cantidad disponible para la talla
            stock.setCantidadDisponible(stock.getCantidadDisponible() + 1);

            RecogidaEquipacion recogida = new RecogidaEquipacion(equipacion, jugador, talla);

            recogidaEquipacionRepository.save(recogida);
        } else {
            throw new Exception("Error al registrar la recogida");
        }

    }
}
