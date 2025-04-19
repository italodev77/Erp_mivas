package dev.enterprisemanager.mivas.modules.client.entity;

import dev.enterprisemanager.mivas.modules.client.enums.RegimeTributario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "TB_clientes")
@Builder
public class client {
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
    private RegimeTributario regimeTributario;
    private boolean ativo;

    public client() {
    }

    public client(Long id, String razaoSocial, String nomeFantasia, String cnpj, String ie, String rua, String numero, String bairro, String municipio, String codMunicipio, String uf, String cep, String telefone, RegimeTributario regimeTributario, boolean ativo) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.ie = ie;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.municipio = municipio;
        this.codMunicipio = codMunicipio;
        this.uf = uf;
        this.cep = cep;
        this.telefone = telefone;
        this.regimeTributario = regimeTributario;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public RegimeTributario getRegimeTributario() {
        return regimeTributario;
    }

    public void setRegimeTributario(RegimeTributario regimeTributario) {
        this.regimeTributario = regimeTributario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
