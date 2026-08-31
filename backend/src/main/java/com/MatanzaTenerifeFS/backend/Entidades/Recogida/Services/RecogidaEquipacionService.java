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
    private final JugadorRepository jugadorRepository;
    private final EquipacionRepository equipacionRepository;
    private final AsignacionEquipacionRepository asignacionEquipacionRepository;

    public RecogidaEquipacionService(RecogidaEquipacionRepository recogidaEquipacionRepository, JugadorRepository jugadorRepository, EquipacionRepository equipacionRepository, AsignacionEquipacionRepository asignacionEquipacionRepository) {
        this.recogidaEquipacionRepository = recogidaEquipacionRepository;
        this.jugadorRepository = jugadorRepository;
        this.equipacionRepository = equipacionRepository;
        this.asignacionEquipacionRepository = asignacionEquipacionRepository;
    }

    // Método para la recogida de equipaciones
    @Transactional
    public void recogerEquipacion(int jugadorId, int equipacionId, Talla talla) throws Exception {

        // Busco las asignaciones que el jugador posee
        try{
            AsignacionEquipacion asignacion = asignacionEquipacionRepository.findByJugador_PlayerIdAndEquipacion_EquipacionIdAndTallaAndEstado(
                    jugadorId, equipacionId, talla, Estado.ENTREGADA
            );

            // Cambio el estado de la asignación
            asignacion.setEstado(Estado.DEVUELTA);

            // Creo el registro de la recogida
            RecogidaEquipacion recogida = new RecogidaEquipacion(
                    asignacion.getEquipacion(),
                    asignacion.getJugador(),
                    asignacion.getTalla()
            );

            // Guardo la recogida
            recogidaEquipacionRepository.save(recogida);

        } catch (RuntimeException e) {
            throw new RuntimeException("El jugador no posee esa equipación. " + e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
