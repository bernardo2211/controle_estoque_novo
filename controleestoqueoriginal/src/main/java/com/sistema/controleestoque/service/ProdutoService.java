package com.sistema.controleestoque.service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.controleestoque.model.Produto;
import com.sistema.controleestoque.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
     private List<ProdutoUpdateInfo> updatedProducts = new ArrayList<>();

    public Produto updateProductQuantity(Long codigoProduto, int quantidade) {
        Produto produto = produtoRepository.findById(codigoProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        int originalQuantity = produto.getQuantidade();
        produto.setQuantidade(originalQuantity - quantidade);
        produtoRepository.save(produto);

        updatedProducts.add(new ProdutoUpdateInfo(produto, quantidade, LocalDateTime.now()));
        return produto;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public List<ProdutoUpdateInfo> getUpdatedProducts() {
        return new ArrayList<>(updatedProducts);
    }

    public void clearUpdatedProducts() {
        updatedProducts.clear();
    }

    public static class ProdutoUpdateInfo {
        private final Produto produto;
        private final int subtractedQuantity;
        private final LocalDateTime updateTimestamp;

        public ProdutoUpdateInfo(Produto produto, int subtractedQuantity, LocalDateTime updateTimestamp) {
            this.produto = produto;
            this.subtractedQuantity = subtractedQuantity;
            this.updateTimestamp = updateTimestamp;
        }

        public Produto getProduto() {
            return produto;
        }

        public int getSubtractedQuantity() {
            return subtractedQuantity;
        }

        public LocalDateTime getUpdateTimestamp() {
            return updateTimestamp;
        }
    }
   
    
}
