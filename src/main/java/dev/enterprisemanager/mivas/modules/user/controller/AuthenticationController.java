package dev.enterprisemanager.mivas.modules.user.controller;

import dev.enterprisemanager.mivas.config.security.TokenService;
import dev.enterprisemanager.mivas.modules.user.dto.AuthDTO;
import dev.enterprisemanager.mivas.modules.user.dto.LoginResponseDTO;
import dev.enterprisemanager.mivas.modules.user.dto.RegisterDTO;
import dev.enterprisemanager.mivas.modules.user.entity.User;
import dev.enterprisemanager.mivas.modules.user.repository.IUserRepository;
import dev.enterprisemanager.mivas.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO authDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((dev.enterprisemanager.mivas.modules.user.entity.User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO registerDTO){
        if(this.userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.name(),registerDTO.email(), registerDTO.role(),encryptedPassword);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}