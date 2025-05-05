package dev.enterprisemanager.mivas.modules.Empresas.repository;

import dev.enterprisemanager.mivas.modules.Empresas.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
    Optional<Empresas> findByCnpj(String cnpj);
}
