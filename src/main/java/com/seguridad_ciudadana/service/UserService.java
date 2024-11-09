package com.seguridad_ciudadana.service;

import com.seguridad_ciudadana.dto.UserDto;
import com.seguridad_ciudadana.entity.GrupoVecinos;
import com.seguridad_ciudadana.entity.Usuario;
import com.seguridad_ciudadana.repository.GrupoVecinosRepository;
import com.seguridad_ciudadana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GrupoVecinosRepository grupoVecinosRepository;
    public void createUser(UserDto usuarioDto, Long groupId) {
        // Buscar el grupo por ID
        Optional<GrupoVecinos> grupoOpt = grupoVecinosRepository.findById(groupId);

        if (grupoOpt.isPresent()) {
            GrupoVecinos grupoVecinos = grupoOpt.get();

            // Crear el usuario y asignarle el grupo
            Usuario usuario = Usuario.builder()
                    .apellido(usuarioDto.getApellido())
                    .nombre(usuarioDto.getNombre())
                    .email(usuarioDto.getEmail())
                    .contrasena(usuarioDto.getContrasena())
                    .telefono(usuarioDto.getTelefono())
                    .direccion(usuarioDto.getDireccion())
                    .grupoVecinos(grupoVecinos) // Asignar el grupo al usuario
                    .build();

            // Guardar el usuario
            userRepository.save(usuario);
        } else {
            // Si el grupo no existe, lanzar una excepción o manejar el error
            throw new IllegalArgumentException("El grupo no existe");
        }
    }

    public UserDto getUserById(Long id) {
        Usuario userResponse=userRepository.findById(id).orElse(null);
        UserDto userDto=UserDto.builder()
                .id(userResponse.getId())
                .apellido(userResponse.getApellido())
                .nombre(userResponse.getNombre())
                .email(userResponse.getEmail())
                .telefono(userResponse.getTelefono())
                .direccion(userResponse.getDireccion())
                .build();
        return userDto;
    }
    public List<UserDto> getUsersByGroup(Long groupId) {
        // Suponiendo que tienes un método que obtiene usuarios por groupId
        List<Usuario> usersResponse = userRepository.findAllByGrupoVecinos_Id(groupId);
        List<UserDto> usersDto=usersResponse.stream().map(user -> UserDto.builder()
               .id(user.getId())
               .apellido(user.getApellido())
               .nombre(user.getNombre())
               .email(user.getEmail())
               .telefono(user.getTelefono())
               .direccion(user.getDireccion())
               .build()).collect(java.util.stream.Collectors.toList());
        return usersDto;
    }
    public boolean login(String telefono, String contrasena) {
        Optional<Usuario> usuarioOpt = userRepository.findByTelefono(telefono);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            return usuario.getContrasena().equals(contrasena);
        } else {
            return false;
        }
    }


}
