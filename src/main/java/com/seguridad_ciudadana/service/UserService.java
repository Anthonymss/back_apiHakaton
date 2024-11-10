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
    public void createUser(UserDto usuarioDto) {
            Usuario usuario = Usuario.builder()
                    .apellido(usuarioDto.getApellido())
                    .nombre(usuarioDto.getNombre())
                    .email(usuarioDto.getEmail())
                    .contrasena(usuarioDto.getContrasena())
                    .telefono(usuarioDto.getTelefono())
                    .direccion(usuarioDto.getDireccion())
                    .build();

            userRepository.save(usuario);
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
    public UserDto login(String telefono, String contrasena) {
        Optional<Usuario> usuarioOpt = userRepository.findByTelefono(telefono);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            usuario.getContrasena().equals(contrasena);
            return UserDto.builder()
                    .id(usuarioOpt.get().getId())
                    .apellido(usuario.getApellido())
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .telefono(usuario.getTelefono())
                    .direccion(usuario.getDireccion())
                    .build();
        }
        return null;

    }


}
