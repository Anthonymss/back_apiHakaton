package com.seguridad_ciudadana.service;

import com.seguridad_ciudadana.dto.MensajeGlobalDto;
import com.seguridad_ciudadana.entity.GrupoVecinos;
import com.seguridad_ciudadana.entity.MensajeGlobal;
import com.seguridad_ciudadana.entity.Usuario;
import com.seguridad_ciudadana.repository.GrupoVecinosRepository;
import com.seguridad_ciudadana.repository.MensajeGlobalRepository;
import com.seguridad_ciudadana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGlobalService {

    private final MensajeGlobalRepository mensajeGlobalRepository;
    private final GrupoVecinosRepository grupoVecinosRepository;
    private final UserRepository usuarioRepository;

    public String enviarMensaje(Long grupoId, Long usuarioId, String contenido) {
        System.out.println(contenido);
        try {
            GrupoVecinos grupoVecinos = grupoVecinosRepository.findById(grupoId)
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
            Usuario remitente = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            MensajeGlobal mensaje = MensajeGlobal.builder()
                    .remitente(remitente)
                    .contenido(contenido)
                    .grupoVecinos(grupoVecinos)
                    .build();
            mensajeGlobalRepository.save(mensaje);
            return "ms enviado correctamente";
        }catch (Exception e){
            return "mensaje no enviado error";
        }
    }

    public List<MensajeGlobalDto> obtenerMensajesPorGrupo(Long grupoId) {
        GrupoVecinos grupoVecinos = grupoVecinosRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        List<MensajeGlobal> mensajes = mensajeGlobalRepository.findByGrupoVecinos(grupoVecinos);

        return mensajes.stream()
                .map(mensaje -> MensajeGlobalDto.builder()
                        .id(mensaje.getId())
                        .fechaHora(mensaje.getFechaHora())
                        .remitenteNombre(mensaje.getRemitente().getNombre())
                        .contenido(mensaje.getContenido())
                        .build())
                .collect(Collectors.toList());
    }
}
