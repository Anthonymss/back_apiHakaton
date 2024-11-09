package com.seguridad_ciudadana.controller;

import com.seguridad_ciudadana.dto.LoginRequest;
import com.seguridad_ciudadana.dto.UserDto;
import com.seguridad_ciudadana.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto, @RequestParam Long groupId) {
        userService.createUser(userDto, groupId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/grupo/{groupId}")
    public ResponseEntity<List<UserDto>> getUsersByGroup(@PathVariable Long groupId) {
        List<UserDto> usersDto = userService.getUsersByGroup(groupId);
        if (!usersDto.isEmpty()) {
            return ResponseEntity.ok(usersDto);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.login(loginRequest.getTelefono(), loginRequest.getContrasena());
        if (isAuthenticated) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
