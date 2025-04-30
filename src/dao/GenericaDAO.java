/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author otoniel.aalves
 * @param <Entidade>
 */
public class GenericaDAO<Entidade> implements Serializable {
    
  
    private EntityManagerFactory emf;
    private EntityManager em = null;
       

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    // SALVAR ENTIDADE GENERICA ////////////////////////////////////////////////
    public void salvar(Entidade entidade) {

        try {
            em = getEM();
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (RuntimeException erro) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw erro;
        } finally {
            em.close();
        }
    }

    // ALTERA ENTIDADE GENERICA ////////////////////////////////////////////////
    public void update(Entidade entidade) {

        try {
            em = getEM();
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (RuntimeException erro) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw erro;
        } finally {
            em.close();
        }
    }

    // EXCLUIR ENTIDADE ///////////////////////////////////////////////////////
    public void excluir(Class<Entidade> entidade, int id) {

        try {
            em = getEM();
            em.getTransaction().begin();
            Entidade obj = em.find(entidade, id);
            em.remove(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // EXCLUIR ENTIDADE ///////////////////////////////////////////////////////
    public Entidade consultaPorID(Class<Entidade> entidade, int id) {

        Entidade entidadeR = null;
        try {
            em = getEM();
            entidadeR = em.find(entidade, id);
        } catch (RuntimeException erro) {
            System.out.println(erro);
        } finally {
            em.close();
        }
        return entidadeR;
    }

    // BUSTA TODA LISTAGEM ////////////////////////////////////////////////////
    public List<Entidade> getListagem(Class<Entidade> entidade) {

        em = getEM();
        String jpql = "FROM " + entidade.getSimpleName() + " x";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

}
