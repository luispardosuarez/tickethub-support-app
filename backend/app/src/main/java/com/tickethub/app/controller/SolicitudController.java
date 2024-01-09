package com.tickethub.app.controller;

import com.tickethub.app.model.Solicitud;
import com.tickethub.app.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    // Obtener todas las solicitudes
    @GetMapping
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        return ResponseEntity.ok(solicitudService.findAllSolicitudes());
    }

    // Obtener una solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Long id) {
        return solicitudService.findSolicitudById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva solicitud
    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(solicitudService.saveOrUpdateSolicitud(solicitud));
    }

    // Actualizar una solicitud existente
    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitud) {
        return solicitudService.findSolicitudById(id)
                .map(existingRequest -> {
                    solicitud.setId(id);
                    return ResponseEntity.ok(solicitudService.saveOrUpdateSolicitud(solicitud));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una solicitud
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
        return ResponseEntity.ok().build();
    }
}

