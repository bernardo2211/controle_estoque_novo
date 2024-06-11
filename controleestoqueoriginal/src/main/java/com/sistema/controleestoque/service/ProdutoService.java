package com.sistema.controleestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.controleestoque.model.Produto;
import com.sistema.controleestoque.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void reduzirEstoque(Long codigoProduto, int quantidade) {
        Produto produto = produtoRepository.findById(codigoProduto)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        int novaQuantidade = produto.getQuantidade() - 150;
        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
        produto.setQuantidade(novaQuantidade);
        produtoRepository.save(produto);
    }

}