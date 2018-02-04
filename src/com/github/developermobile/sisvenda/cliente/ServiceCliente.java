package com.github.developermobile.sisvenda.cliente;

import java.util.List;

/**
 *
 * @author tiago
 */
public class ServiceCliente {
    
    // Instancia da classe ClienteDAO
    private static ClienteDAO clienteDAO;
    
    /** 
     Insere um cliente no banco de dados
     @param cliente objeto cliente para ser inserido no db */
    public static boolean incluiCliente(Cliente cliente) {
        clienteDAO = new ClienteDAO();
        return clienteDAO.incluiCliente(cliente);
    }
    
    /** 
     Busca todos os clientes no banco de dados */
    public static List<Cliente> consultaCliente() {
        clienteDAO = new ClienteDAO();
        return clienteDAO.consultaCliente();
    }
    
    /** 
     Busca um cliente pelo nome no banco de dados
     @param nome nome do cliente para ser consultado no db */
    public static List<Cliente> consultaCliente(String nome) {
        clienteDAO = new ClienteDAO();
        return clienteDAO.consultaCliente(nome);
    }
    
    /** 
     Altera um cliente no banco de dados
     @param cliente objeto cliente para ser alterado no db */
    public static boolean alteraCliente(Cliente cliente) {
        clienteDAO = new ClienteDAO();
        return clienteDAO.alteraCliente(cliente);
    }
    
    /** 
     Exclui um cliente no banco de dados
     @param cliente objeto cliente para ser excluído do db */
    public static boolean excluiCliente(Cliente cliente) {
        clienteDAO = new  ClienteDAO();
        return clienteDAO.excluiCliente(cliente);
    }
    
}
