/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Naty
 */
@Entity
public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataMovimentacao;
    @ManyToOne
    private Setor setor;
    private String solicitante;
    private String chamado;
    @Column(columnDefinition = "longtext")
    private String descricaoDetalhada;
      @Column(columnDefinition = "longtext")
    private String conclusao;
    private String protocolo;
    private String executor;
    @ManyToOne
    private Usuario usuario;


    @OneToMany(mappedBy = "movimentacao", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MovimentacaoItem> itens;

    public Movimentacao() {
    }

    public Movimentacao(int id, Date dataMovimentacao, Setor setor, String solicitante, String chamado, String descricaoDetalhada,String conclusao, String protocolo, String executor, Usuario usuario) {
        this.id = id;
        this.dataMovimentacao = dataMovimentacao;
        this.setor = setor;
        this.solicitante = solicitante;
        this.chamado = chamado;
        this.descricaoDetalhada = descricaoDetalhada;
        this.protocolo = protocolo;
        this.executor = executor;
        this.usuario = usuario;
        this.conclusao = conclusao;

    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getConclusao() {
        return conclusao;
    }
    
    

    public List<MovimentacaoItem> getItens() {
        return itens;
    }

    public void setItens(List<MovimentacaoItem> itens) {
        this.itens = itens;
    }

    public String getDataFormatada() { // DATA FORMATADA 1
        if (this.dataMovimentacao != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(dataMovimentacao);
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
    }

    public String getDataFormatada2() { // DATA FORMATADA 2
        if (this.dataMovimentacao != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dataMovimentacao);
        } else {
            return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        }
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getChamado() {
        return chamado;
    }

    public void setChamado(String chamado) {
        this.chamado = chamado;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
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
        if (!(object instanceof Movimentacao)) {
            return false;
        }
        Movimentacao other = (Movimentacao) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

}
