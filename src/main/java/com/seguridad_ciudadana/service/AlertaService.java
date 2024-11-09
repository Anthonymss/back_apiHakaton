package com.seguridad_ciudadana.service;

import com.seguridad_ciudadana.dto.AlertaDto;
import com.seguridad_ciudadana.entity.Alerta;
import com.seguridad_ciudadana.entity.GrupoVecinos;
import com.seguridad_ciudadana.entity.Usuario;
import com.seguridad_ciudadana.repository.AlertaRepository;
import com.seguridad_ciudadana.repository.GrupoVecinosRepository;
import com.seguridad_ciudadana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final GrupoVecinosRepository grupoVecinosRepository;
    private final UserRepository userRepository;

    public String crearAlerta(Long grupoId, Long usuarioId, String tipo, String descripcion) {
        try {
            GrupoVecinos grupoVecinos = grupoVecinosRepository.findById(grupoId)
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
            Usuario usuario = userRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Alerta alerta = new Alerta();
            alerta.setTipo(tipo);
            alerta.setDescripcion(descripcion);
            alerta.setGrupoVecinos(grupoVecinos);
            alerta.setUsuario(usuario);
            alertaRepository.save(alerta);
            return "Se genero corrrectamente";
        }catch (Exception e){
            return "Error al crear la alerta: " + e.getMessage();
        }
    }

    public List<AlertaDto> obtenerAlertasPorGrupo(Long grupoId) {
        GrupoVecinos grupoVecinos = grupoVecinosRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        List<Alerta> alertas = alertaRepository.findByGrupoVecinos(grupoVecinos);

        return alertas.stream()
                .map(alerta -> AlertaDto.builder()
                        .id(alerta.getId())
                        .fechaHora(alerta.getFechaHora())
                        .descripcion(alerta.getDescripcion())
                        .tipo(alerta.getTipo())
                        .build())
                .collect(Collectors.toList());
    }

}