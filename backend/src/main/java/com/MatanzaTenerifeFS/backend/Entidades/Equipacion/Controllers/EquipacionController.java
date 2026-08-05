package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Controllers;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces.IEquipacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/equipaciones")
public class EquipacionController {

    private final IEquipacionService equipacionService;

    public EquipacionController(IEquipacionService equipacionService) {
        this.equipacionService = equipacionService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<?> findAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(equipacionService.findAll());
    }
}
