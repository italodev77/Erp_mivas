package dev.enterprisemanager.mivas.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class MultiTenantDataSourceConfig {

    @Bean
    public DataSource dataSource(DataSourceProvider provider) {
        TenantRoutingDataSource routingDataSource = new TenantRoutingDataSource(provider);
        routingDataSource.setTargetDataSources((Map<Object, Object>) (Map<?, ?>) provider.getAllDataSources());
        routingDataSource.setDefaultTargetDataSource(masterDataSource());
        return routingDataSource;
    }

    @Bean
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/masterdb")
                .username("postgres")
                .password("postgres")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
