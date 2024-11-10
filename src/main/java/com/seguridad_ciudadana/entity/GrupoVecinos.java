package com.seguridad_ciudadana.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class GrupoVecinos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej. "Vecinos de la Cuadra 5"
    private String descripcion;

    @OneToMany(mappedBy = "grupoVecinos")
    private List<Usuario> miembros; // Miembros del grupo de vecinos

    @OneToMany(mappedBy = "grupoVecinos")
    private List<Alerta> alertas; // Lista de alertas enviadas por los vecinos

    @OneToMany(mappedBy = "grupoVecinos")
    private List<Notificacion> notificaciones;
}
