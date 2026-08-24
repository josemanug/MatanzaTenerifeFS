package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Controller;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.DTOs.AsignacionEquipacionRequest;
import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Interfaces.IAsignacionEquipacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/asignaciones")
public class AsignacionEquipacionController {

    private final IAsignacionEquipacionService asignacionEquipacionService;

    public AsignacionEquipacionController(IAsignacionEquipacionService asignacionEquipacionService) {
        this.asignacionEquipacionService = asignacionEquipacionService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> asignarEquipacion(@RequestBody AsignacionEquipacionRequest request){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(asignacionEquipacionService.asignarEquipacion(
                            request.playerId(),
                            request.equipacionId(),
                            request.talla()
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }

    }
}
