package com.seguridad_ciudadana.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private GrupoVecinos grupoVecinos;

    private String tipo; // Ej. "Robo", "Incendio", "Emergencia Médica"
    private String descripcion;

    @CreationTimestamp
    private LocalDateTime fechaHora;
}