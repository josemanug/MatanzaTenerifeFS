package com.MatanzaTenerifeFS.backend.Entidades.Recogida.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.AsignacionEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.Estado;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Repositories.AsignacionEquipacionRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Repositories.EquipacionRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Repositories.JugadorRepository;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Interfaces.IRecogidaEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Models.RecogidaEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Repositories.RecogidaEquipacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RecogidaEquipacionService implements IRecogidaEquipacionService {

    private final RecogidaEquipacionRepository recogidaEquipacionRepository;
    private final AsignacionEquipacionRepository asignacionEquipacionRepository;

    public RecogidaEquipacionService(RecogidaEquipacionRepository recogidaEquipacionRepository, AsignacionEquipacionRepository asignacionEquipacionRepository) {
        this.recogidaEquipacionRepository = recogidaEquipacionRepository;
        this.asignacionEquipacionRepository = asignacionEquipacionRepository;
    }

    // Método para la recogida de equipaciones
    @Transactional
    public void recogerEquipacion(int jugadorId, int asignacionId) {

        AsignacionEquipacion asignacion = asignacionEquipacionRepository.findById(asignacionId)
                        .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        if (asignacion.getJugador().getPlayerId() != jugadorId) {
            throw new RuntimeException("El jugador no posee esa asignación");
        }

        if (asignacion.getEstado() != Estado.ENTREGADA) {
            throw new RuntimeException("La equipación no está en estado ENTREGADA");
        }

        Equipacion equipacion = asignacion.getEquipacion();
        Talla talla = asignacion.getTalla();

        asignacion.setEstado(Estado.DEVUELTA);

        StockPorTalla stock =
                equipacion.getStockPorTalla().get(talla);

        if (stock == null) {
            throw new RuntimeException("La equipación no tiene stock para la talla " + talla);
        }

        stock.setCantidadDisponible(stock.getCantidadDisponible() + 1);

        RecogidaEquipacion recogida = new RecogidaEquipacion(
                        equipacion,
                        asignacion.getJugador(),
                        talla
                );

        recogidaEquipacionRepository.save(recogida);
    }

}
