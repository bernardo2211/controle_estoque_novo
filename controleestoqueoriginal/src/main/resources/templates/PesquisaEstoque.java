import java.util.HashMap;
import java.util.Map;

public class PesquisaEstoque {

    public static String pesquisarProduto(Map<String, Integer> estoque, String produto) {
        
        Integer quantidade = estoque.get(produto);
        if (quantidade != null) {
            return "Produto: " + produto + ", Quantidade em estoque: " + quantidade;
        } else {
            return "Produto não encontrado no estoque.";
        }
    }

    public static void main(String[] args) {
        // Criando um mapa para representar o estoque (produto, quantidade)
        Map<String, Integer> estoque = new HashMap<>();
        estoque.put("", 10);
        estoque.put("", 20);
        estoque.put("", 15);
        estoque.put("", 50);

        String produto = ""; // Produto a ser pesquisado

        String resultado = pesquisarProduto(estoque, produto); // Chamada do método de pesquisa

        System.out.println(resultado);
    }
}
