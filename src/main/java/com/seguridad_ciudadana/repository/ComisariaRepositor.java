package com.seguridad_ciudadana.repository;

import com.seguridad_ciudadana.entity.Comisaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisariaRepositor extends JpaRepository<Comisaria,Long> {
}
