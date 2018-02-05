
package com.github.developermobile.sisvenda.produto;
import java.util.List;

/**
 *
 * @author tiago
 */
public class ServiceProduto {
    
    // Instancia da classe Produto
    private static ProdutoDAO produtoDAO;
    
    /** 
     Insere um produto no banco de dados
     @param produto objeto produto para ser inserido no db */
    public static boolean incluiProduto(Produto produto) {
        produtoDAO = new ProdutoDAO();
        return produtoDAO.incluiProduto(produto);
    }
    
    /** 
     Busca todos os produtos no banco de dados */
    public static List<Produto> consultaProduto() {
        produtoDAO = new ProdutoDAO();
        return produtoDAO.consultaProduto();
    }
    
    /** 
     Busca um produto pelo nome no banco de dados
     @param nome nome do produto para ser consultado no db */
    public static List<Produto> consultaProduto(String nome) {
        produtoDAO = new ProdutoDAO();
        return produtoDAO.consultaProduto(nome);
    }
    
    /** 
     Altera um produto no banco de dados
     @param produto objeto cliente para ser alterado no db */
    public static boolean alteraProduto(Produto produto) {
        produtoDAO = new ProdutoDAO();
        return produtoDAO.alteraProduto(produto);
    }
    
    /** 
     Exclui um produto no banco de dados
     @param produto  objeto produto para ser excluído do db */
    public static boolean excluiProduto(Produto produto) {
        produtoDAO = new  ProdutoDAO();
        return produtoDAO.excluiProduto(produto);
    }
    
}
