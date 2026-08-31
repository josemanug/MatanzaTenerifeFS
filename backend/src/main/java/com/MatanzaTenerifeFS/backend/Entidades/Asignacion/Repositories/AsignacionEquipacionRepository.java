package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Repositories;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.AsignacionEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.Estado;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionEquipacionRepository extends JpaRepository<AsignacionEquipacion, Integer> {
    List<AsignacionEquipacion> findByJugador_PlayerIdAndEstado(int playerId, Estado estado);

    AsignacionEquipacion findByJugador_PlayerIdAndEquipacion_EquipacionIdAndTallaAndEstado(int jugadorId, int equipacionId, Talla talla, Estado estado);
}
