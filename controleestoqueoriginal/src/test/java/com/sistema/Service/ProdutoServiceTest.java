package com.sistema.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sistema.controleestoque.model.Produto;
import com.sistema.controleestoque.repository.ProdutoRepository;
import com.sistema.controleestoque.service.ProdutoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testReduzirEstoque_Success() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setQuantidade(100);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        produtoService.reduzirEstoque(1L, 50);

        verify(produtoRepository, times(1)).findById(1L);
        verify(produtoRepository, times(1)).save(produto);
        assertEquals(50, produto.getQuantidade());
    }

    @Test
    public void testReduzirEstoque_ProductNotFound() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> produtoService.reduzirEstoque(1L, 50));
    }

    @Test
    public void testReduzirEstoque_InsufficientStock() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setQuantidade(10);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        assertThrows(IllegalArgumentException.class, () -> produtoService.reduzirEstoque(1L, 50));
    }
}

