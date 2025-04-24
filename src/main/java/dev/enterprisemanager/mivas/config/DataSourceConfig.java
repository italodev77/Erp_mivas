package dev.enterprisemanager.mivas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    private final DataSourceProvider dataSourceProvider;

    public DataSourceConfig(DataSourceProvider dataSourceProvider) {
        this.dataSourceProvider = dataSourceProvider;
    }

    @Bean
    public DataSource dataSource() {
        TenantRoutingDataSource routingDataSource = new TenantRoutingDataSource();

        DataSource defaultDataSource = DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/enterpriseDB")
                .username("postgres")
                .password("postgres")
                .driverClassName("org.postgresql.Driver")
                .build();

        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        routingDataSource.setTargetDataSources(new HashMap<>());
        return routingDataSource;
    }
}