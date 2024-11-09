package com.seguridad_ciudadana.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MensajeGlobal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario remitente; // Usuario que envió el mensaje

    private String contenido;
    @CreationTimestamp
    private LocalDateTime fechaHora;

    @ManyToOne
    private GrupoVecinos grupoVecinos; // Grupo de vecinos en el que se envía el mensaje
}
