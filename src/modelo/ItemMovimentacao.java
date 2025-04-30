/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author otoniel.aalves
 */
@Entity
public class ItemMovimentacao implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemMovimentacao;
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "movimentacao_id")
    private Movimentacao movimentacao;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ItemMovimentacao() {
    }

    public ItemMovimentacao(Integer idItemMovimentacao, int quantidade, Movimentacao movimentacao, Produto produto) {
        this.idItemMovimentacao = idItemMovimentacao;
        this.quantidade = quantidade;
        this.movimentacao = movimentacao;
        this.produto = produto;
    }

    public Integer getIdItemMovimentacao() {
        return idItemMovimentacao;
    }

    public void setIdItemMovimentacao(Integer idItemMovimentacao) {
        this.idItemMovimentacao = idItemMovimentacao;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idItemMovimentacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemMovimentacao other = (ItemMovimentacao) obj;
        if (!Objects.equals(this.idItemMovimentacao, other.idItemMovimentacao)) {
            return false;
        }
        return true;
    }

}
