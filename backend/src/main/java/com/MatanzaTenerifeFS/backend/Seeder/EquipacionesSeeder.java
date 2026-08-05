package com.MatanzaTenerifeFS.backend.Seeder;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionCreateDTO;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces.IEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EquipacionesSeeder implements CommandLineRunner {

    private final IEquipacionService equipacionService;

    public EquipacionesSeeder(IEquipacionService equipacionService) {
        this.equipacionService = equipacionService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedEquipacion();
    }

    private void seedEquipacion() throws Exception {

        if(!equipacionService.existsByCodEquipacion("CamAzulPor")){
            EquipacionCreateDTO equipacion = new EquipacionCreateDTO(
                    "CamAzulPor",
                    "Camisa Azul de Portero",
                    Map.of(
                            Talla.XL, 3,
                            Talla.L, 1,
                            Talla.S, 4,
                            Talla.M, 2
                    )
            );

            equipacionService.createEquipacion(equipacion);
        }
    }
}
