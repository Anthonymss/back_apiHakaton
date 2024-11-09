package com.seguridad_ciudadana.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class AlertaDto {
    private Long id;
    private String tipo;
    private String descripcion;
    private LocalDateTime fechaHora;
}
