package dev.enterprisemanager.mivas.modules.enterprise.entity;

import dev.enterprisemanager.mivas.modules.client.enums.RegimeTributario;
import dev.enterprisemanager.mivas.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "enterprise")
@Getter
@Setter
@NoArgsConstructor
public class Enterprise {

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

    private String pathdb;

    // Relacionamento: Uma empresa pode ter vários usuários
    @OneToMany(mappedBy = "enterprise")
    private List<User> users;

    // Construtor customizado (opcional)
    public Enterprise(String municipio, String bairro, String numero, String ie, String nomeFantasia, String razaoSocial, Long id, String cnpj, String rua, String codMunicipio, String cep, String uf, String telefone, RegimeTributario regimeTributario, boolean ativo, String pathdb) {
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
        this.pathdb = pathdb;
    }
}
