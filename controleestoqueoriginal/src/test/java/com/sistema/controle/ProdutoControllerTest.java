package com.sistema.controle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    private MockMvc mockMvc;

    @Test
    public void testAtualizarProdutos_Success() throws Exception {
        Map<String, String> parametros = new HashMap<>();
        parametros.put("quantidade[1]", "50");
        parametros.put("quantidade[2]", "30");

        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();

        mockMvc.perform(put("/produtos/atualizar").param("quantidade[1]", "50").param("quantidade[2]", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pesquisa"));

        verify(produtoService, times(1)).reduzirEstoque(1L, 50);
        verify(produtoService, times(1)).reduzirEstoque(2L, 30);
    }
}

