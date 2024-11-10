package com.seguridad_ciudadana.controller;
import com.seguridad_ciudadana.dto.MensajeGlobalDto;
import com.seguridad_ciudadana.service.ChatGlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-global")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ChatGlobalController {
    private final ChatGlobalService chatGlobalService;
    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestParam Long grupoId, @RequestParam Long usuarioId, @RequestBody String contenido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatGlobalService.enviarMensaje(grupoId, usuarioId, contenido));
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<MensajeGlobalDto>> obtenerMensajesPorGrupo(@PathVariable Long grupoId) {
        List<MensajeGlobalDto> mensajesDto = chatGlobalService.obtenerMensajesPorGrupo(grupoId);
        if (mensajesDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(mensajesDto);
        }
    }



}
