package com.seguridad_ciudadana.repository;

import com.seguridad_ciudadana.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findAllByGrupoVecinos_Id(Long groupId);

    Optional<Usuario> findByTelefono(String telefono);
}
