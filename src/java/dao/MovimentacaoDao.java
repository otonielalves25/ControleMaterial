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
import modelo.Movimentacao;

/**
 *
 * @author Naty
 */
public class MovimentacaoDao extends GenericDAO<Movimentacao> {

    private EntityManager em;

    // BUSCA PAGINADA
    public List<Movimentacao> buscaPaginada(int nrPage, int limite) {
         em = Conexao.getConexao();
        Query query = em.createQuery("FROM Movimentacao m", Movimentacao.class);
        query.setFirstResult(nrPage);
        query.setMaxResults(limite);
        return query.getResultList();
    }

    // BUSCA QUANTOS REGISTROS NO BANCO
    public Long quantosRegistros() {
         em = Conexao.getConexao();
        Query query = em.createQuery("SELECT COUNT(m) FROM Movimentacao m", Movimentacao.class);
        Long retultado = (Long) query.getSingleResult();
        em.close();
        return retultado;
    }

    // Filtro avançado
    public List<Movimentacao> filtroAvancado(String pesquisa, String tipoPesquisa) {
        Query query;
        em = Conexao.getConexao();

        switch (tipoPesquisa) {
            case "chamado":
                query = em.createQuery("SELECT m FROM Movimentacao m WHERE m.chamado LIKE ?1", Movimentacao.class);
                break;
            case "solicitante":
                query = em.createQuery("SELECT m FROM Movimentacao m WHERE m.solicitante LIKE ?1", Movimentacao.class);
                break;
            case "setor":
                query = em.createQuery("SELECT m FROM Movimentacao m WHERE m.setor.nome LIKE ?1", Movimentacao.class);
                break;
            case "descricao":
                query = em.createQuery("SELECT m FROM Movimentacao m WHERE m.descricaoDetalhada LIKE ?1", Movimentacao.class);
                break;
            case "executor":
                query = em.createQuery("SELECT m FROM Movimentacao m WHERE m.executor LIKE ?1", Movimentacao.class);
                break;
            case "data":
                query = em.createQuery("SELECT m FROM Movimentacao m WHERE m.dataMovimentacao LIKE ?1", Movimentacao.class);
                break;
            default:
                query = em.createQuery("FROM Movimentacao m", Movimentacao.class);
        }

        query.setParameter(1, "%" + pesquisa + "%");
        return query.getResultList();
    }

}
