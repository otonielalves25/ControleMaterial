/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Fornecedor;

/**
 *
 * @author Naty
 */
public class FornecedorDao extends GenericDAO<Fornecedor> {

    private EntityManager em;


    // BUSCA POR NOVO
    public Fornecedor buscaPorNome(String nome) {
        Fornecedor retultado = null;
        em = Conexao.getConexao();
        Query query = em.createQuery("SELECT f FROM Fornecedor f WHERE f.nome = ?1", Fornecedor.class);
        query.setParameter(1, nome);
        if (!query.getResultList().isEmpty()) {
            retultado = (Fornecedor) query.getResultList().get(0);
        }
        em.close();
        return retultado;
    }

}
