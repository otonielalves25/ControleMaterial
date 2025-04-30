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

/**
 *
 * @author otoniel.aalves
 */
public class CategoriaDao extends GenericaDAO<Categoria> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Categoria> getListagemOrdenado() {

        try {
            em = getEM();
            TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c ORDER BY c.nomeCategoria", Categoria.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
