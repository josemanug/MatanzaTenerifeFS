package com.MatanzaTenerifeFS.backend.Seeder;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces.IJugadorService;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JugadorSeeder implements CommandLineRunner {
    public JugadorSeeder(IJugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedJugador();
    }

    private final IJugadorService jugadorService;

    private void seedJugador() {
        if(!jugadorService.existsByDNI("51202202Q")){
            Jugador jugador = new Jugador(
                    "José Manuel González Rodríguez",
                    24,
                    605516759,
                    "51202202Q",
                    "SegundaB"
            );
            jugadorService.saveJugador(jugador);
        }

        if(!jugadorService.existsByDNI("51202203V")){
            Jugador jugador = new Jugador(
                    "Adrián González Rodríguez",
                    6,
                    605903721,
                    "51202203V",
                    "Tercera"
            );
            jugadorService.saveJugador(jugador);
        }
    }
}
