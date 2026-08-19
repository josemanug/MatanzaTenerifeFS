package com.MatanzaTenerifeFS.backend.Seeder;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionCreateDTO;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces.IEquipacionService;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.StockPorTalla;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
            Map<Talla, StockPorTalla> stock = new HashMap<>();

            stock.put(Talla.S, new StockPorTalla(4, 4));
            stock.put(Talla.M, new StockPorTalla(2, 2));
            stock.put(Talla.L, new StockPorTalla(1, 1));
            stock.put(Talla.XL, new StockPorTalla(3, 3));

            EquipacionCreateDTO equipacion = new EquipacionCreateDTO(
                    "CamAzulPor",
                    "Camisa Azul de Portero",
                    stock
            );

            equipacionService.createEquipacion(equipacion);
        }
    }
}
