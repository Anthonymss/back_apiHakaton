package com.seguridad_ciudadana.repository;

import com.seguridad_ciudadana.entity.GrupoVecinos;
import com.seguridad_ciudadana.entity.MensajeGlobal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeGlobalRepository extends JpaRepository<MensajeGlobal,Long> {
    List<MensajeGlobal> findByGrupoVecinos(GrupoVecinos grupoVecinos);
}
