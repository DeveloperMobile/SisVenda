package com.github.developermobile.sisvenda.cliente;

import com.github.developermobile.util.JPAUtil;
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
public class ServiceCliente {
     
    /** 
     Insere um cliente no banco de dados
     @param cliente objeto cliente para ser inserido no db */
    public static boolean incluiCliente(Cliente cliente) {
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
    
    /** 
     Busca todos os clientes no banco de dados 
     * @return  */ 
    public static List<Cliente> consultaCliente() {
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
    
    /** 
     Busca um cliente pelo nome no banco de dados
     @param nome nome do cliente para ser consultado no db */
    public static List<Cliente> consultaCliente(String nome) {
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
    
    /** 
     Altera um cliente no banco de dados
     @param cliente objeto cliente para ser alterado no db */
    public static boolean alteraCliente(Cliente cliente) {
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
    
    /** 
     Exclui um cliente no banco de dados
     @param cliente objeto cliente para ser excluído do db */
    public static boolean excluiCliente(Cliente cliente) {
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
    
}
