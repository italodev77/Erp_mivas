package dev.enterprisemanager.mivas.modules.user.controller;

import dev.enterprisemanager.mivas.config.context.DataSourceProvider;
import dev.enterprisemanager.mivas.config.context.TenantContext;
import dev.enterprisemanager.mivas.config.security.TokenService;

import dev.enterprisemanager.mivas.modules.Empresas.entity.Empresas;
import dev.enterprisemanager.mivas.modules.Empresas.repository.EmpresasRepository;
import dev.enterprisemanager.mivas.modules.user.dto.LoginRequest;
import dev.enterprisemanager.mivas.modules.user.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final EmpresasRepository empresasRepository;
    private final DataSourceProvider dataSourceProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 1. Busca empresa no banco master
        Empresas empresa = empresasRepository.findByCnpj(loginRequest.getCnpj())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        // 2. Cria ou reutiliza DataSource para a empresa
        dataSourceProvider.getOrCreateDataSource(
                empresa.getPathDb(),
                empresa.getJdbcUrl(),
                empresa.getDbUser(),
                empresa.getDbPassword()
        );

        // 3. Define tenant no contexto
        TenantContext.setCurrentTenant(empresa.getPathDb());

        try {
            // 4. Autentica o usuário com o banco da empresa
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getSenha()
                    )
            );

            // 5. Define autenticação no contexto
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 6. Gera token JWT
            String jwt = tokenService.generateToken((User) authentication);

            return ResponseEntity.ok(Map.of("token", jwt));
        } finally {
            TenantContext.clear(); // sempre limpe para evitar vazamento de contexto entre requisições
        }
    }
}
