package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.AsignacionEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;

public interface IAsignacionEquipacionService {

    void asignarEquipacion(int jugadorId, int equipacionId, Talla talla);
}
