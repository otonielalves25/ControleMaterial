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
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Categoria;

/**
 *
 * @author Naty
 */
public class CategoriaDao extends GenericDAO<Categoria> {

    private EntityManager em;

    // BUSCA PAGINADA
    public List<Categoria> buscaPaginada(int nrPage, int limite) {
        em = Conexao.getConexao();
        Query query = em.createQuery("FROM Categoria c", Categoria.class);
        query.setFirstResult(nrPage);
        query.setMaxResults(limite);
        return query.getResultList();
    }

    // BUSCA QUANTOS REGISTROS NO BANCO
    public Long quantosRegistros() {
        em = Conexao.getConexao();
        Query query = em.createQuery("SELECT COUNT(c) FROM Categoria c", Categoria.class);
        Long retultado = (Long) query.getSingleResult();
        em.close();
        return retultado;
    }

    // BUSCA POR NOVO
    public Categoria buscaPorNome(String nome) {
        Categoria retultado = null;
        em = Conexao.getConexao();
        Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.nome = ?1", Categoria.class);
        query.setParameter(1, nome);
        if (!query.getResultList().isEmpty()) {
            retultado = (Categoria) query.getResultList().get(0);
        }
        em.close();
        return retultado;
    }

}
