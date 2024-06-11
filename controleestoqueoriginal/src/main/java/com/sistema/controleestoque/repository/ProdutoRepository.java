package com.sistema.controleestoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.controleestoque.model.Produto;

import javafx.beans.value.ObservableObjectValue;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    List<Produto> findAllByOrderByNomeProdutoAsc();

    
    Produto findByCodigoProduto(Long codigoProduto); 
    

   
    
}