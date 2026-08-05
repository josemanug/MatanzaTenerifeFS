package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Controllers;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.DTOs.EquipacionCreateDTO;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Interfaces.IEquipacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createEquipacion(@RequestBody EquipacionCreateDTO equipacionCreateDTO){
        try{
            equipacionService.createEquipacion(equipacionCreateDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }
}
