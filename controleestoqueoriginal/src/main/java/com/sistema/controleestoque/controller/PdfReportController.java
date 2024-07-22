package com.sistema.controleestoque.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sistema.controleestoque.model.Produto;
import com.sistema.controleestoque.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PdfReportController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/generateReport")
public ResponseEntity<InputStreamResource> generateReport() throws DocumentException, IOException {
    List<ProdutoService.ProdutoUpdateInfo> updatedProducts = produtoService.getUpdatedProducts();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Document document = new Document();
    PdfWriter.getInstance(document, out);
    document.open();

    document.add(new Paragraph("Relatório dos Produtos"));

    PdfPTable table = new PdfPTable(6);
    table.setWidthPercentage(100);
    table.setSpacingBefore(10f);
    table.setSpacingAfter(10f);

    String[] headers = {"Código", "Nome", "Data de Retirada", "Unidade (kg)", "Quantidade Retirada", "Fornecedor", };
    for (String header : headers) {
        PdfPCell headerCell = new PdfPCell(new Phrase(header));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);
    }
    document.add(new Paragraph("Ass:________________"));
    document.add(new Paragraph("Curso:_____________________________________"));

    
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    for (ProdutoService.ProdutoUpdateInfo updateInfo : updatedProducts) {
        Produto produto = updateInfo.getProduto();
        table.addCell(produto.getCodigoProduto().toString());
        table.addCell(produto.getNomeProduto());
        table.addCell(updateInfo.getUpdateTimestamp().format(dateFormatter)); 
        table.addCell(produto.getUnidade());
        table.addCell(String.valueOf(updateInfo.getSubtractedQuantity())); 
        table.addCell(produto.getFornecedor());
    }

    document.add(table);
    document.close();

    ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
    HttpHeaders headersPDF = new HttpHeaders();
    headersPDF.add("Content-Disposition", "inline; filename=relatorio.pdf");

    produtoService.clearUpdatedProducts(); 

    return ResponseEntity
            .ok()
            .headers(headersPDF)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
}
}