package com.sistema.controleestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sistema.controleestoque.model.Produto;
import com.sistema.controleestoque.repository.ProdutoRepository;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.ResourceBundle;

@Controller
public class HomeController implements Initializable {

    @Autowired
    ProdutoRepository produtoRepo;

    @Autowired
    private ProdutoRepository acao;

    @FXML
    private TextField txtcodigo, txtnome, txtunidade, txtquantidade, txtfornecedor;
    
    @FXML
    private DatePicker txtdata;

    @FXML
    private Button btsalvar, btatualizar, btdeletar, btcancelar;

    @FXML
    private ListView<Produto> list;

    private Produto item;

    private Long codigo;

    @FXML
    public void salvar() {
        if (!txtnome.getText().trim().isEmpty() && txtdata.getValue() != null) {
            Produto produto = new Produto();
            produto.setNomeProduto(txtnome.getText());
            produto.setUnidade(txtunidade.getText());
            produto.setQuantidade(Integer.parseInt(txtquantidade.getText()));
            produto.setFornecedor(txtfornecedor.getText());
            produto.setDataEntrada(txtdata.getValue());
            produtoRepo.save(produto);
            limpar();
            listar();
        } 
    }

    public void listar(){
        list.setItems(FXCollections.observableArrayList(produtoRepo.findAll()));
    }

    public void delete() {
        acao.delete(item);
        limpar();
        listar();
      }

      @FXML
      public void editar(){
        item.setNomeProduto(txtnome.getText());
        item.setUnidade(txtunidade.getText());
        item.setQuantidade(Integer.parseInt(txtquantidade.getText()));
        item.setFornecedor(txtfornecedor.getText());
        item.setDataEntrada(txtdata.getValue());
        produtoRepo.save(item);
        limpar();
        listar();
      }

      @FXML
      public void limpar(){
        txtcodigo.clear();
        txtnome.clear();
        txtunidade.clear();
        txtquantidade.clear();
        txtfornecedor.clear();
        txtdata.setValue(null);
        
      }

      @GetMapping("/")
      public String index() {
          return "patp"; // Página principal acessível a todos
      }
      
      @GetMapping("/criadores")
      public String criadores() {
          return "criadores"; // Homenagem aos criadores
      }
      
      @GetMapping("/Login")
      public String loginPage(Model model) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          
          boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                  .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
          
          model.addAttribute("isAdmin", isAdmin); // Passa a variável `isAdmin` para o template
          
          return "Login"; // Nome do template de login (ex: login.html)
      }
      
      @GetMapping("/pesquisa")
      public String pesquisa(Model model) {
          List<Produto> produtos = produtoRepo.findAllByOrderByNomeProdutoAsc();
          model.addAttribute("produtos", produtos);
        // Verifica a role do usuário
    boolean isAdmin = false;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    model.addAttribute("isAdmin", isAdmin);
    return "pesquisa"; // Nome da sua view
      }
      @GetMapping("/cadastro")
      public String pesquisaUSER() {
          return "cadastroUsuario"; // Página de cadastro
      }
      @GetMapping("/adicionar")
      public String adicionar() {
          return "produto/adicionarproduto"; // Página para adicionar produtos, restrita a administradores
      }
      

   

   
    public Produto item(){
        return acao.findById(codigo).get();
      }

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listar();   
        list.getSelectionModel().selectedItemProperty().addListener((obs, old, newValue) -> {
            if (newValue != null){
                codigo = newValue.getCodigoProduto();
                item = item();
                txtcodigo.setText(String.valueOf(item.getCodigoProduto()));
                txtnome.setText(item.getNomeProduto());
                txtfornecedor.setText(String.valueOf(item.getFornecedor()));
                txtquantidade.setText(String.valueOf(item.getQuantidade()));
                txtunidade.setText(item.getUnidade());
                LocalDate localDate = item.getDataEntrada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                txtdata.setValue(localDate);
                
            }   
        });
            }
        
}





