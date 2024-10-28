package com.sistema.controleestoque.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.sistema.controleestoque.model.Produto;
import com.sistema.controleestoque.repository.ProdutoRepository;
import com.sistema.controleestoque.service.ProdutoService;

@Controller
@SessionAttributes("updatedProdutos")
public class EstoqueController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    @Autowired
    ProdutoRepository produtoRepo;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listadeprodutos")
    public String listadeproduto(Model model){
        
        List<Produto> produtos = produtoRepo.findAll();
        model.addAttribute("produtos", produtos);

        return "/produto/listadeprodutos";

    }

    @GetMapping("/adicionarproduto")
    public String adicionarproduto(){        
        return "/produto/adicionarproduto";
    }

    @PostMapping(value = "/gravarproduto",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String gravarproduto(@ModelAttribute Produto produto, Model model){
        produtoRepo.save(produto);
        return "redirect:/pesquisa";
    }

    @PostMapping("/atualizarproduto")
public String atualizarProduto(@RequestBody Produto produtoAtualizado) {
    // Verifica se o código do produto foi passado corretamente
    Long codigoProduto = produtoAtualizado.getCodigoProduto();  // Use o getter correto
    
    if (codigoProduto == null) {
        System.out.println("Código do produto não pode ser nulo.");
        return "redirect:/pesquisa"; // Redireciona ou trata o erro
    }

    // Busca o produto no banco pelo código
    Optional<Produto> optionalProduto = produtoRepo.findById(codigoProduto);

    // Se o produto com esse código existir, atualiza os campos
    if (optionalProduto.isPresent()) {
        Produto produto = optionalProduto.get();
        
        // Atualiza os atributos com os novos valores
        produto.setNomeProduto(produtoAtualizado.getNomeProduto());
        produto.setDataEntrada(produtoAtualizado.getDataEntradaAsLocalDate());
        produto.setUnidade(produtoAtualizado.getUnidade());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setFornecedor(produtoAtualizado.getFornecedor());

        // Salva o produto atualizado no banco de dados
        produtoRepo.save(produto);

        System.out.println("Produto atualizado com sucesso: " + codigoProduto);
    } else {
        // Se o produto não existir, exibe uma mensagem de erro
        System.out.println("Produto com o código " + codigoProduto + " não encontrado.");
    }
    
    return "redirect:/pesquisa"; // Redireciona para a página de pesquisa após a atualização
}



    @GetMapping("/excluirproduto/{codigo}")
public String excluirProduto(@PathVariable Long codigo) {
    if (produtoRepo.existsById(codigo)) {
        produtoRepo.deleteById(codigo);
    }
    return "redirect:/pesquisa"; // Redireciona após a exclusão
}





    @PostMapping("/updateQuantities")
    public ModelAndView updateQuantities(@RequestParam("codigosProduto") Long[] codigosProduto, @RequestParam("quantidades") String[] quantidades) {
        if (codigosProduto.length != quantidades.length) {
        }
    
        List<Produto> updatedProdutos = new ArrayList<>();
        for (int i = 0; i < codigosProduto.length; i++) {
            if (!quantidades[i].isEmpty()) {
                int quantidade = Integer.parseInt(quantidades[i]);
                Produto produto = produtoService.updateProductQuantity(codigosProduto[i], quantidade);
                updatedProdutos.add(produto);
            }
        }
        return new ModelAndView("redirect:/generateReport");
    }
    }
