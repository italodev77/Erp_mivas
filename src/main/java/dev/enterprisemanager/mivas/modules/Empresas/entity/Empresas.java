package dev.enterprisemanager.mivas.modules.Empresas.entity;

import dev.enterprisemanager.mivas.modules.Empresas.dto.EmpresasDTO;
import dev.enterprisemanager.mivas.modules.client.enums.RegimeTributario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_empresas")
@Getter
@Setter
@NoArgsConstructor
public class Empresas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String ie;
    private String rua;
    private String numero;
    private String bairro;
    private String municipio;
    private String codMunicipio;
    private String cep;
    private String uf;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private RegimeTributario regimeTributario;

    private boolean ativo;

    private String pathdb;      // identificador do tenant
    private String jdbcUrl;     // URL completa do banco da empresa
    private String dbUser;      // usuário do banco da empresa
    private String dbPassword;  // senha do banco da empresa

    public Empresas(EmpresasDTO empresasDTO) {
    }
}
