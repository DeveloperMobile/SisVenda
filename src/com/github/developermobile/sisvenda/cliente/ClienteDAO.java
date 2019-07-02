package com.github.developermobile.sisvenda.cliente;

import com.github.developermobile.sisvenda.dao.DAO;
import com.github.developermobile.util.JPAUtil;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author tiago
 */
public class ClienteDAO implements DAO<Cliente> {

    @Override
    public boolean inclui(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(cliente);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public boolean altera(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(cliente);
            et.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public boolean exclui(Cliente cliente) {
       EntityManager em = JPAUtil.getEntityManager();
       try {
           EntityTransaction et = em.getTransaction();
           et.begin();
           cliente = em.merge(cliente);
           em.remove(cliente);
           et.commit();
           return true;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           if (em.isOpen()) {
                em.getTransaction().rollback();
            }
           return false;
       } finally {
           JPAUtil.closeEntityManager();
       }
    }

    @Override
    public List<Cliente> consulta() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query q = em.createNamedQuery(Cliente.FIND_ALL, Cliente.class);
            clientes = q.getResultList();
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return new ArrayList<Cliente>();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Cliente> consulta(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query q = em.createNamedQuery(Cliente.FIND_BY_NOME, Cliente.class);
            q.setParameter("nome", "%" + nome + "%");
            clientes = q.getResultList();
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return new ArrayList<Cliente>();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Cliente> consulta(Date dataInicio, Date dataFim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
