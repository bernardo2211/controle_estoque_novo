package com.sistema.controleestoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produto")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada")
    private Date dataEntrada;

    @Column(name = "unidade")
    private String unidade;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "fornecedor")
    private String fornecedor;

    public Long getCodigo() {
        return codigoProduto;
    }

    public void setCodigo(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = Date.from(dataEntrada.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate getDataEntradaAsLocalDate() {
        return dataEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto; // Implementação correta do setter
    }

    public String toString() {
        return "Código: " + codigoProduto + "  -  Nome: " + nomeProduto + "  -  Data de entrada: " + dataEntrada
                + "  -  Unidade de medida: " + unidade + "  -  Quantidade: " + quantidade + "  -  Fornecedor: "
                + fornecedor;
    }

    public void setId(long l) {
        // Você pode remover ou implementar esse método se não for necessário
    }
}
