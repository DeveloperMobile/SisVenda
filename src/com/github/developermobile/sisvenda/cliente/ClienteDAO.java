package com.github.developermobile.sisvenda.cliente;

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
public class ClienteDAO implements DAO<Cliente> {

    @Override
    public boolean inclui(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction tx = (Transaction) em.getTransaction();
            tx.begin();
            em.persist(cliente);
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
    public boolean altera(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Transaction et = (Transaction) em.getTransaction();
            et.begin();
            em.merge(cliente);
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
    public boolean exclui(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
       try {
           Transaction et = (Transaction) em.getTransaction();
           et.begin();
           cliente = em.merge(cliente);
           em.remove(cliente);
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
    public List<Cliente> consulta() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            clientes = q.getResultList();
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Cliente>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> consulta(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class);
            q.setParameter("nome", nome);
            clientes = q.getResultList();
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<Cliente>();
        } finally {
            em.close();
        }
    }
  
    
    
}
