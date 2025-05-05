package dev.enterprisemanager.mivas.config.context;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;



@RequiredArgsConstructor
public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private final DataSourceProvider dataSourceProvider;

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        String currentTenant = (String) determineCurrentLookupKey();
        DataSource dataSource = dataSourceProvider.getAllDataSources().get(currentTenant);

        if (dataSource == null) {
            throw new IllegalStateException("Nenhum DataSource encontrado para tenant: " + currentTenant);
        }
        return dataSource;
    }
}

