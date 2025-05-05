package dev.enterprisemanager.mivas.config.context;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Component
public class DataSourceProvider {

    private final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    // Retorna todos os datasources para o roteador usar
    public Map<String, DataSource> getAllDataSources() {
        return dataSources;
    }

    // Cria ou reutiliza datasource para um tenant
    public DataSource getOrCreateDataSource(String tenantId, String jdbcUrl, String username, String password) {
        return dataSources.computeIfAbsent(tenantId, id -> {
            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .driverClassName("org.postgresql.Driver") // ou outro se necessário
                    .build();
        });
    }
}
