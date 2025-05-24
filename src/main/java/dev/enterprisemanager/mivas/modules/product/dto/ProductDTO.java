package dev.enterprisemanager.mivas.modules.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String codigo;
    private String descricao;
    private String caracteristica;

    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private BigDecimal precoVenda2;
    private BigDecimal precoVenda3;
    private BigDecimal precoFardo;
    private BigDecimal precoCaixa;
    private BigDecimal precoPalete;

    private Integer quantidadeEstoqueReal;
    private Integer quantidadeEstoqueArmazem;
    private Integer quantidadeEstoqueFiscal;

    private Boolean ativo;
}
