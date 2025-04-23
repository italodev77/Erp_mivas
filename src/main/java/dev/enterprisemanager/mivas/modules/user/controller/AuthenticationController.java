package dev.enterprisemanager.mivas.modules.user.controller;

import dev.enterprisemanager.mivas.config.security.TokenService;
import dev.enterprisemanager.mivas.modules.user.dto.AuthDTO;
import dev.enterprisemanager.mivas.modules.user.dto.LoginResponseDTO;
import dev.enterprisemanager.mivas.modules.user.dto.RegisterDTO;
import dev.enterprisemanager.mivas.modules.user.entity.User;
import dev.enterprisemanager.mivas.modules.user.repository.IUserRepository;
import dev.enterprisemanager.mivas.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthDTO authDTO) {
        log.info("Tentativa de login para o email: {}", authDTO.email());
        try {
            var authToken = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
            var authentication = authenticationManager.authenticate(authToken);
            var user = (User) authentication.getPrincipal();
            String token = tokenService.generateToken(user);

            log.info("Login realizado com sucesso para o email: {}", authDTO.email());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            log.warn("Falha ao autenticar usuário com email: {} - {}", authDTO.email(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO registerDTO) {
        log.info("Tentativa de registro para o email: {}", registerDTO.email());
        if (userRepository.findByEmail(registerDTO.email()) != null) {
            log.warn("Tentativa de registro falhou: email já cadastrado - {}", registerDTO.email());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        }

        String encryptedPassword = passwordEncoder.encode(registerDTO.password());
        User newUser = new User(registerDTO.name(), registerDTO.email(), registerDTO.role(), encryptedPassword);
        userRepository.save(newUser);

        log.info("Usuário registrado com sucesso: {}", registerDTO.email());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Listagem de todos os usuários requisitada.");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
