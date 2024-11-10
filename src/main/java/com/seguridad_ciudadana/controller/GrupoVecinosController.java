package com.seguridad_ciudadana.controller;

import com.seguridad_ciudadana.dto.GrupoVecinosDto;
import com.seguridad_ciudadana.service.GrupoVecinosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grupo")
public class GrupoVecinosController {

    private final GrupoVecinosService grupoVecinosService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearGrupo(@RequestBody GrupoVecinosDto grupoVecinosDto) {
        String mensaje = grupoVecinosService.crearGrupo(grupoVecinosDto);
        if (mensaje.equals("Grupo creado")) {
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<GrupoVecinosDto>> getGruposVecinos() {
        List<GrupoVecinosDto> gruposVecinos = grupoVecinosService.getGrupoVecinos();
        return new ResponseEntity<>(gruposVecinos, HttpStatus.OK);
    }
    @PostMapping("/agregar-miembro")
    public ResponseEntity<String> agregarMiembro(
            @RequestParam Long idUser,
            @RequestParam Long idGrupo) {

        String mensaje = grupoVecinosService.agregarMiembro(idUser, idGrupo);

        if (mensaje.equals("Usuario agregado")) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }
}
