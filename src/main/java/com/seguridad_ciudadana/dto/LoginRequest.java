package com.seguridad_ciudadana.dto;

import lombok.Data;

@Data
public class LoginRequest
{
    private String telefono;
    private String contrasena;
}
