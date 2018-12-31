
package com.github.developermobile.sisvenda.venda;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author tiago
 */
public class ServiceVenda {
    
    // Instancia da classe VendaDAO
    private static VendaDAO vendaDAO;
    
    /** 
     Insere uma venda no banco de dados
     @param venda objeto venda para ser inserido no db
     @return true se tudo ok/ false se não ok */
    public static boolean registraVenda(Venda venda) {
        vendaDAO = new VendaDAO();
        return vendaDAO.registraVenda(venda);
    }
    
    /** Busca todas as vendas por periodo no banco de dados
     @param dataInicio data inicial do intervalo das vendas
     @param dataFim  data final do intervalo das vendas
     @return lista de vendas se houver alguma no db */
    public static List<Venda> consultaVendaPeriodo(Date dataInicio, Date dataFim) {
        vendaDAO = new VendaDAO();
        return vendaDAO.consultaVendaPeriodo(dataInicio, dataFim);
    }
    
}
