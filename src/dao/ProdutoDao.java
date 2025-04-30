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
import modelo.Produto;

/**
 *
 * @author otoniel.aalves
 */
public class ProdutoDao extends GenericaDAO<Produto> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Produto> getListagemOrdenadoLike(String pesquisa) {

        try {
            em = getEM();
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.nomeProduto LIKE ?1", Produto.class);
            query.setParameter(1, "%" + pesquisa + "%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * ADICIONA QUANTIDADE AO PRODUTO //////////////////////////////////////////
     *
     * @param quantidade
     * @param produto
     */
    public void adicionaQuantidade(int quantidade, Produto produto) {

        int quantidadeNova = 0;
        quantidadeNova = produto.getQuantidade() + quantidade;

        try {
            em = getEM();
            em.getTransaction().begin();
            TypedQuery<Produto> query = em.createQuery("UPDATE Produto p SET p.quantidade = ?1 WHERE p.idProduto = ?2", Produto.class);
            query.setParameter(1, quantidadeNova);
            query.setParameter(2, produto.getIdProduto());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * REMOVE QUANTIDADE DE MATERIAL ///////////////////////////////////////////
     *
     * @param quantidade
     * @param produto
     */
    public void removeQuantidade(int quantidade, Produto produto) {

        int quantidadeNova = 0;
        quantidadeNova = produto.getQuantidade() - quantidade;

        try {
            em = getEM();
            em.getTransaction().begin();
            TypedQuery<Produto> query = em.createQuery("UPDATE Produto p SET p.quantidade = ?1 WHERE p.idProduto = ?2", Produto.class);
            query.setParameter(1, quantidadeNova);
            query.setParameter(2, produto.getIdProduto());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
