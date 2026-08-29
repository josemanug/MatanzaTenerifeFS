package com.MatanzaTenerifeFS.backend.Entidades.Recogida.Controller;

import com.MatanzaTenerifeFS.backend.Entidades.Recogida.DTOs.RecogidaEquipacionRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Recogida.Interfaces.IRecogidaEquipacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recogidas")
@PreAuthorize("isAuthenticated()")
public class RecogidaEquipacionController {

    private final IRecogidaEquipacionService recogidaEquipacionService;

    public RecogidaEquipacionController(IRecogidaEquipacionService recogidaEquipacionService) {
        this.recogidaEquipacionService = recogidaEquipacionService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> recogerEquipacion(@RequestBody RecogidaEquipacionRequest request){
        try{
            recogidaEquipacionService.recogerEquipacion(
                    request.playerId(),
                    request.equipacionId(),
                    request.talla()
            );
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }
}
