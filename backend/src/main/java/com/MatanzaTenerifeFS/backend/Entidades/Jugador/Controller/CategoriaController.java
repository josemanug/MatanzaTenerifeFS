package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Controller;

import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Interfaces.IJugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categorias")
@PreAuthorize("isAuthenticated()")
public class CategoriaController {

    private final IJugadorService jugadorService;

    public CategoriaController(IJugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping()
    public ResponseEntity<?> getCategorias(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jugadorService.findCategorias());
    }
}
