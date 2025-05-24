package dev.enterprisemanager.mivas.modules.product.mapper;

import dev.enterprisemanager.mivas.modules.product.dto.ProductDTO;
import dev.enterprisemanager.mivas.modules.product.dto.ProductResponseDTO;
import dev.enterprisemanager.mivas.modules.product.entity.Product;

public class ProductMapper {

    // Product -> ProductResponseDTO
    public static ProductResponseDTO toResponseDTO(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponseDTO.builder()
                .id(product.getId())
                .codigo(product.getCodigo())
                .descricao(product.getDescricao())
                .caracteristica(product.getCaracteristica())
                .precoCusto(product.getPrecoCusto())
                .precoVenda(product.getPrecoVenda())
                .precoVenda2(product.getPrecoVenda2())
                .precoVenda3(product.getPrecoVenda3())
                .precoFardo(product.getPrecoFardo())
                .precoCaixa(product.getPrecoCaixa())
                .precoPalete(product.getPrecoPalete())
                .quantidadeEstoqueReal(product.getQuantidadeEstoqueReal())
                .quantidadeEstoqueArmazem(product.getQuantidadeEstoqueArmazem())
                .quantidadeEstoqueFiscal(product.getQuantidadeEstoqueFiscal())
                .ativo(product.getAtivo())
                .dataCriacao(product.getDataCriacao())
                .dataAtualizacao(product.getDataAtualizacao())
                .build();
    }

    // ProductDTO -> Product
    public static Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        return Product.builder()
                .codigo(dto.getCodigo())
                .descricao(dto.getDescricao())
                .caracteristica(dto.getCaracteristica())
                .precoCusto(dto.getPrecoCusto())
                .precoVenda(dto.getPrecoVenda())
                .precoVenda2(dto.getPrecoVenda2())
                .precoVenda3(dto.getPrecoVenda3())
                .precoFardo(dto.getPrecoFardo())
                .precoCaixa(dto.getPrecoCaixa())
                .precoPalete(dto.getPrecoPalete())
                .quantidadeEstoqueReal(dto.getQuantidadeEstoqueReal())
                .quantidadeEstoqueArmazem(dto.getQuantidadeEstoqueArmazem())
                .quantidadeEstoqueFiscal(dto.getQuantidadeEstoqueFiscal())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
    }

    // Product -> ProductDTO
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setCodigo(product.getCodigo());
        dto.setDescricao(product.getDescricao());
        dto.setCaracteristica(product.getCaracteristica());
        dto.setPrecoCusto(product.getPrecoCusto());
        dto.setPrecoVenda(product.getPrecoVenda());
        dto.setPrecoVenda2(product.getPrecoVenda2());
        dto.setPrecoVenda3(product.getPrecoVenda3());
        dto.setPrecoFardo(product.getPrecoFardo());
        dto.setPrecoCaixa(product.getPrecoCaixa());
        dto.setPrecoPalete(product.getPrecoPalete());
        dto.setQuantidadeEstoqueReal(product.getQuantidadeEstoqueReal());
        dto.setQuantidadeEstoqueArmazem(product.getQuantidadeEstoqueArmazem());
        dto.setQuantidadeEstoqueFiscal(product.getQuantidadeEstoqueFiscal());
        dto.setAtivo(product.getAtivo());
        return dto;
    }
}