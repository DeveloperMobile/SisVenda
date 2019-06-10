
package com.github.developermobile.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tiago
 */
public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("SisVendaPU");
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
