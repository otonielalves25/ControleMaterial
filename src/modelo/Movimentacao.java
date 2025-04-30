/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author otoniel.aalves
 */
@Entity
public class Movimentacao implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idMovimentacao;
    private String tipo;
    private String chamado;
    private String solicitante;    
    private String dataMudanca;
    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String observacao;
    @OneToMany
    private List<ItemMovimentacao> itens = new ArrayList<>();

    public Movimentacao() {
    }

    public Movimentacao(Integer idMovimentacao, String tipo, String chamado, String solicitante, String dataMudanca, Setor setor, Usuario usuario, String observacao) {
        this.idMovimentacao = idMovimentacao;
        this.tipo = tipo;
        this.chamado = chamado;
        this.solicitante = solicitante;
        this.dataMudanca = dataMudanca;
        this.setor = setor;
        this.usuario = usuario;
        this.observacao = observacao;
    }

    public Integer getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(Integer idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getChamado() {
        return chamado;
    }

    public void setChamado(String chamado) {
        this.chamado = chamado;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getDataMudanca() {
        return dataMudanca;
    }

    public void setDataMudanca(String dataMudanca) {
        this.dataMudanca = dataMudanca;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ItemMovimentacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemMovimentacao> itens) {
        this.itens = itens;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idMovimentacao);
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
        final Movimentacao other = (Movimentacao) obj;
        if (!Objects.equals(this.idMovimentacao, other.idMovimentacao)) {
            return false;
        }
        return true;
    }
    
    

}
