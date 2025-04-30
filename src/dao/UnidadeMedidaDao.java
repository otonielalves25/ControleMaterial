/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import modelo.Categoria;
import modelo.UnidadeMedida;

/**
 *
 * @author otoniel.aalves
 */
public class UnidadeMedidaDao extends GenericaDAO<UnidadeMedida> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<UnidadeMedida> getListagemOrdenado() {

        try {
            em = getEM();
            TypedQuery<UnidadeMedida> query = em.createQuery("SELECT c FROM UnidadeMedida c ORDER BY c.nomeUnidade", UnidadeMedida.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
