/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author Naty
 */
public class UsuarioDao extends GenericDAO<Usuario> {


    private EntityManager em;

    // ALTENTICANDO O USUARIO //
    public Usuario autenticarUsuario(Usuario usuario) {

        Usuario user = null;
        try {
            em = Conexao.getConexao();
            Query query = em.createQuery("FROM Usuario u WHERE u.login = ?1 AND u.senha = ?2 AND u.ativo = TRUE", Usuario.class);
            query.setParameter(1, usuario.getLogin());
            query.setParameter(2, usuario.getSenha());

            List lista = query.getResultList();
            if (!lista.isEmpty()) {
                user = (Usuario) lista.get(0);
            }

        } catch (Exception e) {

        } finally {
            em.close();
        }
        return user;
    }

    // VERIFICA SE O LOGIN JÁ É CADASTRADO
    public boolean consultaLogin(String login) {
        boolean retorno = false;
        try {
            em = Conexao.getConexao();
            Query query = em.createQuery("FROM Usuario u WHERE u.login = ?1", Usuario.class);
            query.setParameter(1, login);
            List lista = query.getResultList();

            if (!lista.isEmpty()) {
                retorno = true;
            }

        } catch (Exception e) {

        } finally {
            em.close();
        }
        return retorno;
    }

    // VERIFICA SE O LOGIN JÁ É CADASTRADO
    public boolean consultaPorNome(String nome) {
        boolean retorno = false;
        try {
            em = Conexao.getConexao();
            Query query = em.createQuery("FROM Usuario u WHERE u.nome = ?1", Usuario.class);
            query.setParameter(1, nome);
            List lista = query.getResultList();

            if (!lista.isEmpty()) {
                retorno = true;
            }

        } catch (Exception e) {

        } finally {
            em.close();
        }
        return retorno;
    }

    // VERIFICA SE O LOGIN JÁ É CADASTRADO
    public void inativaUsuario(int id) {
        try {
            em = Conexao.getConexao();
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Usuario u SET u.ativo = false WHERE u.id = ?1 AND u.login != ?2", Usuario.class);
            query.setParameter(1, id);
            query.setParameter(2, "admin");
            query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {

        } finally {
            em.close();
        }

    }

}
