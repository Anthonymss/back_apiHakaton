package com.seguridad_ciudadana.controller;
import com.seguridad_ciudadana.dto.AlertaDto;
import com.seguridad_ciudadana.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AlertaController {

    private final AlertaService alertaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearAlerta(@RequestParam Long grupoId, @RequestParam Long usuarioId,
                                                 @RequestParam String tipo, @RequestParam String descripcion) {

        return ResponseEntity.status(HttpStatus.CREATED).body(alertaService.crearAlerta(grupoId, usuarioId, tipo, descripcion));
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<AlertaDto>> obtenerAlertasPorGrupo(@PathVariable Long grupoId) {
        List<AlertaDto> alertas = alertaService.obtenerAlertasPorGrupo(grupoId);
        if (alertas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(alertas);
        }
    }
}