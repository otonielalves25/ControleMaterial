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
import modelo.Setor;

/**
 *
 * @author otoniel.aalves
 */
public class SetorDao extends GenericaDAO<Setor> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Setor> getListagemOrdenado() {

        try {
            em = getEM();
            TypedQuery<Setor> query = em.createQuery("SELECT s FROM Setor s ORDER BY s.nomeSetor", Setor.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
        //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Setor> getListagemOrdenadoLike(String pesquisa) {

        try {
            em = getEM();
            TypedQuery<Setor> query = em.createQuery("SELECT s FROM Setor s WHERE s.nomeSetor LIKE ?1 ORDER BY s.nomeSetor", Setor.class);
            query.setParameter(1, "%"+pesquisa+"%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
