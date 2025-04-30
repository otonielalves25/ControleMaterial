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
import javax.persistence.Query;

/**
 *
 * @author Naty
 * @param <E>
 */
public class GenericDAO<E> implements Serializable {

    private EntityManager em;

    // SALVA 
    public E salvar(E entity) {
        em = Conexao.getConexao();
        try {
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return entity;
    }

    // EXCLUIR    
    public void excluir(int id, Class<E> entity) {
        em = Conexao.getConexao();
        try {
            em.getTransaction().begin();
            E ent = em.find(entity, id);
            em.remove(ent);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // CONSULTA
    public E buscaPorID(int id, Class<E> entity) {
        E entidadeR = null;
        em = Conexao.getConexao();
        try {
            entidadeR = em.find(entity, id);
        } catch (RuntimeException erro) {
        } finally {
            em.close();
        }
        return entidadeR;
    }

    // LISTAR TUDO    
    public List<E> buscaTudo(Class<E> entity) {
        em = Conexao.getConexao();
        try {
            String jpql = "FROM " + entity.getSimpleName() + " X";
            Query query = em.createQuery(jpql);
            List<E> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    // LISTAR POR String    
    public List<?> buscaPorQuery(String queryString) {
        em = Conexao.getConexao();
        try {
            Query query = em.createQuery(queryString);
            List<?> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
