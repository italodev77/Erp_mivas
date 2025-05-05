package dev.enterprisemanager.mivas.modules.Empresas.entity;

import dev.enterprisemanager.mivas.modules.client.enums.RegimeTributario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresa")
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

    // ✅ Campos para multi-tenant
    @Column(name = "path_db")
    private String pathDb;

    @Column(name = "jdbc_url")
    private String jdbcUrl;

    @Column(name = "db_user")
    private String dbUser;

    @Column(name = "db_password")
    private String dbPassword;

    public Empresas(String municipio, String bairro, String numero, String ie, String nomeFantasia, String razaoSocial, Long id, String cnpj, String rua, String codMunicipio, String cep, String uf, String telefone, RegimeTributario regimeTributario, boolean ativo, String pathDb, String jdbcUrl, String dbUser, String dbPassword) {
        this.municipio = municipio;
        this.bairro = bairro;
        this.numero = numero;
        this.ie = ie;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.id = id;
        this.cnpj = cnpj;
        this.rua = rua;
        this.codMunicipio = codMunicipio;
        this.cep = cep;
        this.uf = uf;
        this.telefone = telefone;
        this.regimeTributario = regimeTributario;
        this.ativo = ativo;
        this.pathDb = pathDb;
        this.jdbcUrl = jdbcUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
}
