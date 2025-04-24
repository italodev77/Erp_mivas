package dev.enterprisemanager.mivas.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.enterprisemanager.mivas.modules.enterprises.entity.Enterprise;
import dev.enterprisemanager.mivas.modules.enterprises.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class DataSourceProvider {

    private final EnterpriseRepository enterpriseRepository;
    private final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    public DataSource getDataSourceForTenant(Long enterpriseId) {
        return dataSources.computeIfAbsent(enterpriseId.toString(), id -> {
            Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                    .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(enterprise.getPathDb());
            config.setUsername("postgres");
            config.setPassword("postgres");
            config.setDriverClassName("org.postgresql.Driver");

            return new HikariDataSource(config);
        });
    }
}