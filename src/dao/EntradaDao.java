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
import modelo.Entrada;

/**
 *
 * @author otoniel.aalves
 */
public class EntradaDao extends GenericaDAO<Entrada> {

    private EntityManagerFactory emf;
    private EntityManager em = null;

    private EntityManager getEM() {
        emf = Conexao.getConexao();
        return emf.createEntityManager();
    }

    //RETORNA ORDENADO /////////////////////////////////////////////////////////
    public List<Entrada> getListagemOrdenadoLike(String pesquisa, String procura) {

        String sql = "";

        switch (procura) {
            case "data":
                sql = "SELECT e FROM Entrada e WHERE e.dataEntrada LIKE ?1";
                break;
            case "material":
                sql = "SELECT e FROM Entrada e WHERE e.produto.nomeProduto LIKE ?1";
                break;
            case "fornecedor":
                sql = "SELECT e FROM Entrada e WHERE e.fornecedor.nomeFornecedor LIKE ?1";
                break;
            case "cadastradoPor":
                sql = "SELECT e FROM Entrada e WHERE e.usuario.nome LIKE ?1";
                break;
            default:
                procura = "";
                break;
        }

        try {
            em = getEM();
            TypedQuery<Entrada> query = em.createQuery(sql, Entrada.class);
            query.setParameter(1, "%" + pesquisa + "%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
