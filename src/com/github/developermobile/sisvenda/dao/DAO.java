
package com.github.developermobile.sisvenda.dao;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author tiago
 * @param <T>
 */
public interface DAO<T> {
    boolean inclui(T t);
    boolean altera(T t);
    boolean exclui(T t);
    List<T> consulta();
    List<T> consulta(String nome);
    List<T> consulta(Date dataInicio, Date dataFim);
}
