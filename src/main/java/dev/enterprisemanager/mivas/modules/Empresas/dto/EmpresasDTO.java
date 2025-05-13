package dev.enterprisemanager.mivas.modules.Empresas.dto;

import dev.enterprisemanager.mivas.modules.client.enums.RegimeTributario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresasDTO {

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

    private RegimeTributario regimeTributario;

    private boolean ativo;


    private String pathdb;      // identificador do tenant


    private String jdbcUrl;     // URL completa do banco da empresa


    private String dbUser;      // usuário do banco da empresa


    private String dbPassword;  // senha do banco da empresa
}