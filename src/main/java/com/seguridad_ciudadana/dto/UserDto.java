package com.seguridad_ciudadana.dto;

import com.seguridad_ciudadana.entity.ContactoEmergencia;
import com.seguridad_ciudadana.entity.GrupoVecinos;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contrasena;
    private String direccion;

    private List<ContactoEmergenciaDto> contactosEmergencia;
}
