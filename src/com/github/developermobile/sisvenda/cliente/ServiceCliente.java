package com.github.developermobile.sisvenda.cliente;

import java.util.List;

/**
 *
 * @author tiago
 */
public class ServiceCliente {
     
    public static ClienteDAO dao;
    
    /** 
     Insere um cliente no banco de dados
     @param cliente objeto cliente para ser inserido no db */
    public static boolean incluiCliente(Cliente cliente) {
        dao = new ClienteDAO();
        return dao.inclui(cliente);
    }
    
    /** 
     Busca todos os clientes no banco de dados 
     * @return  */ 
    public static List<Cliente> consultaCliente() {
        dao = new ClienteDAO();
        return dao.consulta();
    }
    
    /** 
     Busca um cliente pelo nome no banco de dados
     @param nome nome do cliente para ser consultado no db */
    public static List<Cliente> consultaCliente(String nome) {
        dao = new ClienteDAO();
        return dao.consulta(nome);
    }
    
    /** 
     Altera um cliente no banco de dados
     @param cliente objeto cliente para ser alterado no db */
    public static boolean alteraCliente(Cliente cliente) {
        dao = new ClienteDAO();
        return dao.altera(cliente);
    }
    
    /** 
     Exclui um cliente no banco de dados
     @param cliente objeto cliente para ser excluído do db */
    public static boolean excluiCliente(Cliente cliente) {
        dao = new ClienteDAO();
        return dao.exclui(cliente);
    }
    
}
