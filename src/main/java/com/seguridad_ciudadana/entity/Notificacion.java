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
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GrupoVecinos grupoVecinos;

    private String tipo; // Ej. "Alarma", "Comunicado", "Emergencia"
    private String mensaje;
    @CreationTimestamp
    private LocalDateTime fechaHora;
}
