/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tony
 */
public class Conexao {

    private static EntityManagerFactory emf;

    public static EntityManager getConexao() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ControleMaterialPU");
        }
        return emf.createEntityManager();
    }

}
