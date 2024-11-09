package com.seguridad_ciudadana.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comisaria {
    @Id
    private Integer codigoInei;
    private Integer codigoCpnp;
    private String departamento;
    private String provincia;
    private String distrito;
    private Float Latitud;
    private Float Longitud;
    private String macregpol;
    private String regpol;
    private String divpol;
    private String nombre;

}
