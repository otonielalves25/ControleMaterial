/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Naty
 */
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String unidade;

    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private Categoria categoria;
    private int peso;
    @Column(nullable= false, precision=7, scale=2) 
    private BigDecimal valor;
    @Column(columnDefinition = "LONGTEXT")
    private String imagemB64;
    private String extencao;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private int quantidade;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    private boolean excluido;
    private String situacao;  /*NOVO, USADO*/

    public Produto() {
    }

    public Produto(int id, String nome, String unidade, Fornecedor fornecedor, Categoria categoria, int peso, BigDecimal valor, 
            String imagemB64, String extencao, String descricao, int quantidade, Date dataCadastro, boolean excluido, String situacao) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.peso = peso;
        this.valor = valor;
        this.imagemB64 = imagemB64;
        this.extencao = extencao;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
        this.excluido = excluido;
        this.situacao = situacao;
    }

    public Produto(int id, String nome, String unidade, Fornecedor fornecedor, Categoria categoria, int peso, BigDecimal valor, 
            String descricao, int quantidade, Date dataCadastro, boolean excluido, String situacao) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.peso = peso;
        this.valor = valor;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
        this.excluido = excluido;
        this.situacao = situacao;
    }

    public String getValorFormatado() {
        return NumberFormat.getCurrencyInstance().format(this.valor);
    }

    public String getImagemB64Formatada() {
        return "data:image/" + this.extencao + ";base64," + this.imagemB64;
    }

    public String getDataFormatada() { // DATA FORMATADA 1
        if (this.dataCadastro != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(dataCadastro);
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
    }

    public String getDataFormatada2() { // DATA FORMATADA 2
        if (this.dataCadastro != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(dataCadastro);
        } else {
            return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getImagemB64() {
        return imagemB64;
    }

    public void setImagemB64(String imagemB64) {
        this.imagemB64 = imagemB64;
    }

    public String getExtencao() {
        return extencao;
    }

    public void setExtencao(String extencao) {
        this.extencao = extencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "";
    }

}
