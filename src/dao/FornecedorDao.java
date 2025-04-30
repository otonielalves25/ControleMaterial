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
import modelo.Fornecedor;

/**
 *
 * @author otoniel.aalves
 */
public class FornecedorDao extends GenericaDAO<Fornecedor> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Fornecedor> getListagemOrdenado(String procura) {

        try {
            em = getEM();
            TypedQuery<Fornecedor> query = em.createQuery("SELECT f FROM Fornecedor f WHERE f.nomeFornecedor LIKE ?1 ORDER BY f.nomeFornecedor", Fornecedor.class);
            query.setParameter(1, "%" + procura + "%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    //RETORNA DESATIVAR FORNECEDOR /////////////////////////////////////////
    public void desativarFornecedor(int id) {
        try {
            em = getEM();
            em.getTransaction().begin();
            TypedQuery<Fornecedor> query = em.createQuery("UPDATE Fornecedor f SET f.ativo = false WHERE f.idFornecedor = ?1", Fornecedor.class);
            query.setParameter(1, id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

}
