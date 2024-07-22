package com.sistema.controleestoque.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

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
