package dev.enterprisemanager.mivas.modules.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "produtos")

public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "codigo_produto", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "descricao_produto", length = 500)
    private String descricao;

    @Column(name = "caracteristica_produto", nullable = false, length = 150)
    private String caracteristica;

    @Column(name = "preco_custo", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCusto;

    @Column(name = "preco_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda;

    @Column(name = "preco_venda_2", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda2;

    @Column(name = "preco_venda_3", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda3;

    @Column(name = "preco_fardo", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoFardo;

    @Column(name = "preco_caixa", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCaixa;

    @Column(name = "preco_palete", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoPalete;

    @Column(name = "quantidade_estoque_real", nullable = false)
    private Integer quantidadeEstoqueReal;

    @Column(name = "quantidade_estoque_armazem", nullable = false)
    private Integer quantidadeEstoqueArmazem;

    @Column(name = "quantidade_estoque_fiscal", nullable = false)
    private Integer quantidadeEstoqueFiscal;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
