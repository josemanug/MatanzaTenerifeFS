package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.CreateJugadorRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces.IJugadorService;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JugadorService implements IJugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    // Comprobar si existe el jugador
    public boolean existsByDNI(String dni){
        return jugadorRepository.existsByDni(dni);
    }

    // Guradar un jugador
    public void saveJugador(Jugador jugador){
        if(isValidDni(jugador.getDni()) || isValidCategoria(jugador.getCategoria())){
            jugadorRepository.save(jugador);
        } else {
            throw new IllegalArgumentException("El DNI o la categoría no son válidas");
        }
    }


    // Crear un nuevo jugador
    public void createJugador(CreateJugadorRequest createJugadorRequest) throws Exception {
        if(!jugadorRepository.existsByDni(createJugadorRequest.dni())){
            Jugador jugador = new Jugador(
                    createJugadorRequest.nombre(),
                    createJugadorRequest.dorsal(),
                    createJugadorRequest.telefono(),
                    createJugadorRequest.dni(),
                    createJugadorRequest.categoria()
            );
        } else {
            throw new Exception("Jugador ya existente");
        }
    }



    /*
    *
    * Métodos auxiliares
    *
    * */

    // Comprobar si el DNI es válido
    private boolean isValidDni(String dni) {
        if (dni == null) {
            return false;
        }

        dni = dni.trim().toUpperCase();

        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        int number = Integer.parseInt(dni.substring(0, 8));
        char expectedLetter = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(number % 23);

        return dni.charAt(8) == expectedLetter;
    }

    // Si la categoría es válida

    private boolean isValidCategoria(String categoria){
        // Array con las posibles categorías
        List<String> categorias = List.of(
                "SegundaB", "Tercera", "Juvenil", "Cadete", "Infantil", "Alevín", "Benjamín"
        );

        return categorias.contains(categoria);


    }


}
