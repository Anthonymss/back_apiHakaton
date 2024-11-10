package com.seguridad_ciudadana.service;

import com.seguridad_ciudadana.dto.GrupoVecinosDto;
import com.seguridad_ciudadana.entity.GrupoVecinos;
import com.seguridad_ciudadana.entity.Usuario;
import com.seguridad_ciudadana.repository.GrupoVecinosRepository;
import com.seguridad_ciudadana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GrupoVecinosService {
    private final GrupoVecinosRepository grupoVecinosRepository;
    private final UserRepository userRepository;
    public String crearGrupo(GrupoVecinosDto grupoVecinosDto){
        try {
            GrupoVecinos g=GrupoVecinos.builder()
                    .nombre(grupoVecinosDto.getNombre())
                    .descripcion(grupoVecinosDto.getDescripcion())
                    .build();
            this.grupoVecinosRepository.save(g);
            return "Grupo creado";
        }catch (Exception e){
            return "Grupo no creado";
        }
    }
    public List<GrupoVecinosDto> getGrupoVecinos(){
        return grupoVecinosRepository.findAll().stream().map(
                x->GrupoVecinosDto.builder()
                        .id(x.getId())
                        .nombre(x.getNombre())
                        .descripcion(x.getDescripcion())
                        .build()
        ).collect(Collectors.toList());
    }
    public String agregarMiembro(Long idUser,Long idGrupo){
        Optional<Usuario> op = userRepository.findById(idUser);
        if(op.isPresent()){
            Usuario u=op.get();
            GrupoVecinos g=grupoVecinosRepository.findById(idGrupo).get();
            u.setGrupoVecinos(g);
            userRepository.save(u);
            return "Usuario agregado";
        }
        return "Error";
    }
}
