package dev.enterprisemanager.mivas.modules.user.controller;

import dev.enterprisemanager.mivas.modules.user.dto.RegisterDTO;
import dev.enterprisemanager.mivas.modules.user.dto.UserDTO;
import dev.enterprisemanager.mivas.modules.user.entity.User;
import dev.enterprisemanager.mivas.modules.user.repository.IUserRepository;
import dev.enterprisemanager.mivas.modules.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Listagem de todos os usuários requisitada.");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        log.info("Remoção de usuário com ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Atualização do cadastro do usuário com ID: {}", id);
        try {
            User updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o usuário.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterDTO registerRequest,
            @AuthenticationPrincipal User authenticatedUser
    ) {
        // 1. Verifica se o e-mail já existe
        if (userRepository.existsByEmail(registerRequest.email())) {
            return ResponseEntity.badRequest().body(Map.of("error", "E-mail já cadastrado"));
        }

        // 2. Cria novo usuário no mesmo tenant do usuário logado
        User newUser = new User();
        newUser.setName(registerRequest.name());
        newUser.setEmail(registerRequest.email());
        newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
        newUser.setRole(registerRequest.role());
        newUser.setEnterprise(authenticatedUser.getEnterprise());

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Usuário registrado com sucesso"));
    }
}
