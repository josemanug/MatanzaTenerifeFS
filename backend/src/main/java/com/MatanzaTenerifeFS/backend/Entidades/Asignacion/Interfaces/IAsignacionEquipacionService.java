package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.DTOs.AsignacionEquipacionResponse;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.AsignacionEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.AsignadasResponse;

import java.util.List;

public interface IAsignacionEquipacionService {

    void asignarEquipacion(int jugadorId, int equipacionId, Talla talla);

    List<AsignacionEquipacionResponse> obtenerEquipacionesPendientes(int playerId);
}
