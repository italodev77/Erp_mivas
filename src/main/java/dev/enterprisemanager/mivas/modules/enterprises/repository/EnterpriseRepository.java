package dev.enterprisemanager.mivas.modules.enterprises.repository;

import dev.enterprisemanager.mivas.modules.enterprises.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
