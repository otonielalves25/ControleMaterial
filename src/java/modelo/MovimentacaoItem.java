/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Naty
 */
@Entity
public class MovimentacaoItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;    
    @ManyToOne
    private Produto produto;
    private int quantidade;
    
    @ManyToOne()
    @JoinColumn(name = "movimentacao_id")
    Movimentacao movimentacao;      

    public MovimentacaoItem() {
    }

    public MovimentacaoItem(int id, Produto produto, int quantidade, Movimentacao movimentacao) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.movimentacao = movimentacao;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimentacaoItem)) {
            return false;
        }
        MovimentacaoItem other = (MovimentacaoItem) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "";
    }

}
