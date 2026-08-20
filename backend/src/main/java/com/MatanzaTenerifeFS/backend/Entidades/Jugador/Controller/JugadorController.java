package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Controller;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces.IJugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jugador")
public class JugadorController {

    private final IJugadorService jugadorService;

    public JugadorController(IJugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jugadorService.findAll());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }
}
