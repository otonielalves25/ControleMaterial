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
import modelo.Usuario;

/**
 *
 * @author otoniel.aalves
 */
public class UsuarioDao extends GenericaDAO<Usuario> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Usuario> getListagemOrdenado() {

        try {
            em = getEM();
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.nome", Usuario.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }

    }

    // DESANTIVANDO O USUARIO NO SISTEMA ///////////////////////////////////////
    public void desativarUsuario(int id) {
        try {
            em = getEM();
            em.getTransaction().begin();
            TypedQuery<Usuario> query = em.createQuery("UPDATE Usuario u SET u.ativo = false WHERE u.idUsuario = ?1", Usuario.class);
            query.setParameter(1, id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    // DESANTIVANDO O USUARIO NO SISTEMA ///////////////////////////////////////
    public Usuario autenticarUsuario(String login, String senha) {
        try {
            em = getEM();
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = ?1 AND u.senha = ?2", Usuario.class);
            query.setParameter(1, login);
            query.setParameter(2, senha);
            List lista = query.getResultList();
            if (lista.size() > 0) {
                return (Usuario) lista.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }

    }

    // DESANTIVANDO O USUARIO NO SISTEMA ///////////////////////////////////////
    public boolean trocarSenha(int idUsuario, String senhaNova) {
        boolean retorno = false;
        try {
            em = getEM();
            em.getTransaction().begin();
            TypedQuery<Usuario> query = em.createQuery("UPDATE Usuario u SET u.senha = ?1 WHERE u.idUsuario = ?2", Usuario.class);
            query.setParameter(1, senhaNova);
            query.setParameter(2, idUsuario);
            query.executeUpdate();
            retorno = true;
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return retorno;
    }

}
