package com.seguridad_ciudadana.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactoEmergenciaDto {
    private Long id;
    private String nombre;
    private String telefono;
    private String tipo;
}
