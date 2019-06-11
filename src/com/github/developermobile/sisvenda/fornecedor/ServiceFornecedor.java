package com.github.developermobile.sisvenda.fornecedor;

import com.github.developermobile.sisvenda.cliente.*;
import java.util.List;

/**
 *
 * @author tiago
 */
public class ServiceFornecedor {
    
    // Instancia da classe ClienteDAO
    private static FornecedorDAO fornecedorDAO;
    
    /** 
     Insere um fornecedor no banco de dados
     @param fornecedor objeto fornecedor para ser inserido no db */
    public static boolean incluiFornecedor(Fornecedor fornecedor) {
        fornecedorDAO = new FornecedorDAO();
        return fornecedorDAO.inclui(fornecedor);
    }
    
    /** 
     Busca todos os fornecedores no banco de dados */
    public static List<Fornecedor> consultaFornecedor() {
        fornecedorDAO = new FornecedorDAO();
        return fornecedorDAO.consulta();
    }
    
    /** 
     Busca um fornecedor pelo nome no banco de dados
     @param nome nome do fornecedor para ser consultado no db */
    public static List<Fornecedor> consultaFornecedor(String nome) {
        fornecedorDAO = new FornecedorDAO();
        return fornecedorDAO.consulta(nome);
    }
    
    /** 
     Altera um fornecedor no banco de dados
     @param fornecedor objeto fornecedor para ser alterado no db */
    public static boolean alteraFornecedor(Fornecedor fornecedor) {
        fornecedorDAO = new FornecedorDAO();
        return fornecedorDAO.altera(fornecedor);
    }
    
    /** 
     Exclui um cliente no banco de dados
     @param fornecedor objeto fornecedor para ser excluído do db */
    public static boolean excluiFornecedor(Fornecedor fornecedor) {
        fornecedorDAO = new  FornecedorDAO();
        return fornecedorDAO.exclui(fornecedor);
    }
    
}
