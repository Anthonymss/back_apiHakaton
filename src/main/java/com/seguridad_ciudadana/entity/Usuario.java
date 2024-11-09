package com.seguridad_ciudadana.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    @Column(unique = true)
    private String telefono;
    private String contrasena;
    private String direccion;
    @ManyToOne
    private GrupoVecinos grupoVecinos;
    @OneToMany
    private List<ContactoEmergencia> contactosEmergencia;
}