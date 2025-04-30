/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.SetorTipo;

/**
 *
 * @author Naty
 */
public class SetorTipoDao extends GenericDAO<SetorTipo> {

    private EntityManager em;

    // BUSCA POR NOVO
    public SetorTipo buscaPorNome(String nome) {
        SetorTipo retultado = null;
        em = Conexao.getConexao();
        Query query = em.createQuery("SELECT f FROM SetorTipo f WHERE f.nome = ?1", SetorTipo.class);
        query.setParameter(1, nome);
        if (!query.getResultList().isEmpty()) {
            retultado = (SetorTipo) query.getResultList().get(0);
        }
        em.close();
        return retultado;
    }

}
