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
import modelo.Produto;

/**
 *
 * @author Naty
 */
public class ProdutoDao extends GenericDAO<Produto> {

    private EntityManager em;

    // LISTA produtos LIKE 
    public List<Produto> listaMaterialLike(String busca) {
        List<Produto> listagem = null;
        try {
            em = Conexao.getConexao();
            Query query = em.createQuery("SELECT p FROM Produto p WHERE p.nome like ?1 OR p.categoria.nome like ?2", Produto.class);
            query.setParameter(1, "%" + busca + "%");
            query.setParameter(2, "%" + busca + "%");
            listagem = query.getResultList();

        } catch (Exception e) {
        } finally {
            em.close();
        }
        return listagem;
    }

    // LISTA produtos LIKE 
    public void atualizarEstoque(Produto produto, int quantidadeNova) {
        try {
            em = Conexao.getConexao();
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Produto p SET p.quantidade = ?1 WHERE p.id = ?2", Produto.class);
            query.setParameter(1, quantidadeNova);
            query.setParameter(2, produto.getId());
            query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
        } finally {
            em.close();
        }

    }

}
