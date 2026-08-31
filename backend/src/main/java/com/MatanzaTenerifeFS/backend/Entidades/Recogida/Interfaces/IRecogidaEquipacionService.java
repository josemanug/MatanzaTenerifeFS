package com.MatanzaTenerifeFS.backend.Entidades.Recogida.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Models.RecogidaEquipacion;

public interface IRecogidaEquipacionService {

    void recogerEquipacion(int jugadorId, int asignacionId);
}
