package com.github.developermobile.sisvenda.fornecedor;

import com.github.developermobile.sisvenda.cliente.Cliente;
import com.github.developermobile.sisvenda.dao.DAO;
import com.github.developermobile.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Transaction;

/**
 *
 * @author tiago
 */
public class FornecedorDAO implements DAO<Fornecedor> {

    @Override
    public boolean inclui(Fornecedor fornecedor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            em.persist(fornecedor);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean altera(Fornecedor fornecedor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction et = (Transaction) em.getTransaction();
            et.begin();
            em.merge(fornecedor);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean exclui(Fornecedor fornecedor) {
        EntityManager em = JPAUtil.getEntityManager();
       try {
           Transaction et = (Transaction) em.getTransaction();
           et.begin();
           fornecedor = em.merge(fornecedor);
           em.remove(fornecedor);
           et.commit();
           return true;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return false;
       } finally {
           em.close();
       }
    }

    @Override
    public List<Fornecedor> consulta() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class);
            fornecedores = q.getResultList();
            return fornecedores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Fornecedor>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Fornecedor> consulta(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT f FROM Fornecedor f WHERE f.nome = :nome", Fornecedor.class);
            q.setParameter("nome", nome);
            fornecedores = q.getResultList();
            return fornecedores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Fornecedor>();
        } finally {
            em.close();
        }
    }
    
}
