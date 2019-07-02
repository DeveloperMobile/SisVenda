
package com.github.developermobile.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author tiago
 */
public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT = "SisVendaPU";
    
    private static ThreadLocal<EntityManager> threadEntityManager =
            new ThreadLocal<EntityManager>();
    
    private static EntityManagerFactory emf;

    private JPAUtil() {}
    
    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        EntityManager em = threadEntityManager.get();
        
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            threadEntityManager.set(em);
        }
        return em;
    }
    
    public static void closeEntityManager() {
        EntityManager em = threadEntityManager.get();
        
        if (em != null) {
            EntityTransaction transaction = em.getTransaction();
            
            if (transaction.isActive()) {
                transaction.commit();
            }
            
            em.close();
            threadEntityManager.set(null);
        }
    }
    
    public static void closeEntityManagerFactory() {
        closeEntityManager();
        emf.close();
    }
}
