package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Controllers;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tallas")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TallaController {

    @GetMapping()
    public Talla[] getTallas(){
        return Talla.values();
    }
}
