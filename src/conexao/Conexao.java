/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author otoniel.aalves
 */
public class Conexao {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getConexao() {
        if ((emf == null) || (!emf.isOpen())) {
            emf = Persistence.createEntityManagerFactory("ControleMaterialPU"); //COLOQUE O NOME UNIDADE DE PERSISTENCIA                    // VEJA NO ARQUIVO DE PERSITENCIA .................................................................................
        }
        return emf;
    }

}
