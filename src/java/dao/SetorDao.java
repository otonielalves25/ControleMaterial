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
import modelo.Setor;

/**
 *
 * @author Naty
 */
public class SetorDao extends GenericDAO<Setor> {

    
    private EntityManager em;

    // LISTA SETORES LIKE 
    public List<Setor> listaSetorLike(String setor) {
        List<Setor> listagem = null;
        try {
            em = Conexao.getConexao();
            Query query = em.createQuery("SELECT c FROM Setor c WHERE c.nome like ?1", Setor.class);
            query.setParameter(1, "%" + setor + "%");
            listagem = query.getResultList();

        } catch (Exception e) {
        } finally {
            em.close();
        }
        return listagem;
    }

}
