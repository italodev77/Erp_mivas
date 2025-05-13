package dev.enterprisemanager.mivas.modules.Empresas.service;

import dev.enterprisemanager.mivas.modules.Empresas.dto.EmpresasDTO;
import dev.enterprisemanager.mivas.modules.Empresas.entity.Empresas;
import dev.enterprisemanager.mivas.modules.Empresas.repository.EmpresasRepository;
import dev.enterprisemanager.mivas.config.context.DataSourceProvider;
import org.springframework.stereotype.Service;

@Service
public class EmpresasService {

    private final EmpresasRepository empresasRepository;
    private final DataSourceProvider dataSourceProvider;

    public EmpresasService(EmpresasRepository empresasRepository, DataSourceProvider dataSourceProvider) {
        this.empresasRepository = empresasRepository;
        this.dataSourceProvider = dataSourceProvider;
    }

    public Empresas registerEmpresa(EmpresasDTO empresasDTO) {
        // Cria a nova entidade Empresas usando o DTO
        Empresas empresa = new Empresas(empresasDTO);

        // Persistir a empresa no banco master
        Empresas savedEmpresa = empresasRepository.save(empresa);

        // Criar o DataSource para a empresa, se necessário
        dataSourceProvider.getOrCreateDataSource(
                empresa.getPathdb(),
                empresa.getJdbcUrl(),
                empresa.getDbUser(),
                empresa.getDbPassword()
        );

        return savedEmpresa;
    }
}