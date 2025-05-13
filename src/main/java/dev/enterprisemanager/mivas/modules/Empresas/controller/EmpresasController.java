package dev.enterprisemanager.mivas.modules.Empresas.controller;

import dev.enterprisemanager.mivas.modules.Empresas.dto.EmpresasDTO;
import dev.enterprisemanager.mivas.modules.Empresas.entity.Empresas;
import dev.enterprisemanager.mivas.modules.Empresas.service.EmpresasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresasController {

    private final EmpresasService empresasService;

    public EmpresasController(EmpresasService empresasService) {
        this.empresasService = empresasService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody EmpresasDTO empresasDTO) {
        try {
            // Registra a empresa e cria o DataSource
            Empresas savedEmpresa = empresasService.registerEmpresa(empresasDTO);
            return ResponseEntity.ok(savedEmpresa);
        } catch (Exception e) {
            // Tratar exceção caso ocorra
            return ResponseEntity.status(500).body("Erro ao registrar empresa: " + e.getMessage());
        }
    }
}
