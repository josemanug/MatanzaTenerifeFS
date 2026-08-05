package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Repositories;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipacionRepository extends JpaRepository<Equipacion, Integer> {
    boolean existsByCodEquipacion(String codEquipacion);
}
