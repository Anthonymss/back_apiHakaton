package com.seguridad_ciudadana.repository;

import com.seguridad_ciudadana.entity.Alerta;
import com.seguridad_ciudadana.entity.GrupoVecinos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta,Long> {
    List<Alerta> findByGrupoVecinos(GrupoVecinos grupoVecinos);
}
