package dev.enterprisemanager.mivas.modules.enterprise.repository;

import dev.enterprisemanager.mivas.modules.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findByCnpj(String cnpj);
}
