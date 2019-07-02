
package com.github.developermobile.sisvenda.produto;

import com.github.developermobile.sisvenda.dao.DAO;
import com.github.developermobile.util.JPAUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Transaction;

/**
 *
 * @author tiago
 */
public class ProdutoDAO implements DAO<Produto> {

    @Override
    public boolean inclui(Produto t) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            em.persist(t);
            tx.commit();
            return true;
        } catch  (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean altera(Produto t) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction et = (Transaction) em.getTransaction();
            et.begin();
            em.merge(t);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean exclui(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction et = (Transaction) em.getTransaction();
            et.begin();
            produto = em.merge(produto);
            em.remove(produto);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> consulta() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Produto> produtos = new ArrayList<>();
        try {
            Query q = em.createQuery("select p from Produto p", Produto.class);
            produtos = q.getResultList();
            return produtos;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return new ArrayList<Produto>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> consulta(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Produto> produtos = new ArrayList<>();
        try {
            Query q = em.createQuery("select p from Produto p where p.nome like :nome", Produto.class);
            produtos = q.getResultList();
            return produtos;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return new ArrayList<Produto>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produto> consulta(Date dataInicio, Date dataFim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
