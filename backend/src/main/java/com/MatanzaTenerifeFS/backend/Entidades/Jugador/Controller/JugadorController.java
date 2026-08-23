package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Controller;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.CreateJugadorRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.DTOs.UpdateJugadorRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces.IJugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jugadores")
@PreAuthorize("isAuthenticated()")
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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jugadorService.findById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e);
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createJugador(@RequestBody CreateJugadorRequest createJugadorRequest){
        try{
            jugadorService.createJugador(createJugadorRequest);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateJugador(@RequestBody UpdateJugadorRequest updateJugadorRequest, @PathVariable int id){
        try{
            jugadorService.updateJugador(updateJugadorRequest, id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }
}
