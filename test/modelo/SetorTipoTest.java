/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

/**
 *
 * @author Naty
 */
public class SetorTipoTest {

    EntityManagerFactory emf;
    EntityManager em;

    public SetorTipoTest() {
        emf = Persistence.createEntityManagerFactory("ControleMaterialPU");
        em = emf.createEntityManager();

    }

    @Test
    public void testGetSetores() {

        SetorTipo tip = new SetorTipo();
      
        tip.setNome("teste");

        try {

            em.getTransaction().begin();
            em.merge(tip);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

}
