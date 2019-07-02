
package com.github.developermobile.sisvenda.venda;

import com.github.developermobile.sisvenda.dao.DAO;
import com.github.developermobile.util.JPAUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.hibernate.Transaction;

/**
 *
 * @author tiago
 */
public class VendaDAO implements DAO<Venda>{

    @Override
    public boolean inclui(Venda t) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(t);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if (em.isOpen()) { em.getTransaction().rollback(); }
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public boolean altera(Venda t) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(t);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if (em.isOpen()) { em.getTransaction().rollback(); }
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public boolean exclui(Venda t) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            EntityTransaction et = (Transaction) em.getTransaction();
            et.begin();
            t = em.merge(t);
            em.remove(t);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if (em.isOpen()) { em.getTransaction().rollback(); }
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Venda> consulta() {
        return null;
    }

    @Override
    public List<Venda> consulta(String nome) {
        return null;
    }

    @Override
    public List<Venda> consulta(Date dataInicio, Date dataFim) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Venda> vendas = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT v FROM Venda v WHERE v.dataVenda between :dataInicio AND :dataFim", Venda.class);
            q.setParameter("dataInicio", dataInicio);
            q.setParameter("dataFim", dataFim);
            vendas = q.getResultList();
            return vendas;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if (em.isOpen()) { em.getTransaction().rollback(); }
            return new ArrayList();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
}
